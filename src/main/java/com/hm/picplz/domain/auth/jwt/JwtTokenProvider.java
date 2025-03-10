package com.hm.picplz.domain.auth.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider implements InitializingBean {

    private final RedisTemplate<String, String> redisTemplate;
    // JWT 생성 및 검증을 위한 키
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;
    private final String secret;
    private Key key;

    public JwtTokenProvider(
            RedisTemplate<String, String> redisTemplate, @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.redisTemplate = redisTemplate;
        this.secret = secret;
        this.accessTokenValidityInMilliseconds = tokenValidityInSeconds * 30; // 60,000ms : 1m(0.001d), 60000 * 30 = 30m
        this.refreshTokenValidityInMilliseconds = tokenValidityInSeconds * 60; // 60,000ms : 1m(0.001d), 60000 * 60 * 24 * 2 = 2d
    }

    // 빈이 생성되고 주입을 받은 후에 secret값을 Base64 Decode해서 key 변수에 할당하기 위해
    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public JwtTokenResponseDto generateTokenDto(Authentication authentication, String loginType) {

        // 권한들 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // 토큰의 expire 시간을 설정
        long now = (new Date()).getTime();
        Date accessExprTime = new Date(now + this.accessTokenValidityInMilliseconds);
        Date refreshExprTime = new Date(now + this.refreshTokenValidityInMilliseconds);

        DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        Object memberId = defaultOAuth2User.getAttributes().get("memberNo");
        Object provider = defaultOAuth2User.getAttributes().get("provider");

        String accessToken = Jwts.builder()
                .setSubject(String.valueOf(memberId))
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(accessExprTime)
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(String.valueOf(memberId))
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(refreshExprTime)
                .compact();

        redisTemplate.opsForValue().set(
                "token : " + String.valueOf(memberId),
                refreshToken,
                refreshTokenValidityInMilliseconds,
                TimeUnit.MILLISECONDS
        );

        return JwtTokenResponseDto.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpires(this.accessTokenValidityInMilliseconds - 5000) // 자동 로그아웃 기능을 위한 간극
                .accessTokenExpiresDate(accessExprTime)
                .build();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        Long memberId = Long.valueOf(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(memberId, token, authorities);
    }

    // 토큰의 유효성 검증을 수행
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            e.printStackTrace();
            System.out.println("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            System.out.println("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
            System.out.println("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

}

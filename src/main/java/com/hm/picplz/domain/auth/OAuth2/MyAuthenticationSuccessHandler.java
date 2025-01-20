package com.hm.picplz.domain.auth.OAuth2;

import com.hm.picplz.domain.auth.jwt.JwtTokenProvider;
import com.hm.picplz.domain.auth.jwt.JwtTokenResponseDto;
import com.hm.picplz.domain.member.MemberRepository;
import com.hm.picplz.domain.member.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.sql.DriverManager;

@RequiredArgsConstructor
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
//    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        Object memberNo = defaultOAuth2User.getAttributes().get("memberNo");

        if (defaultOAuth2User == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.springframework.security.oauth2.core.user.OAuth2User");
        } else {
            try {
                if (authentication.isAuthenticated()) {
                    Member member = memberRepository.findById((Long) memberNo)
                            .orElseThrow(() -> new IllegalArgumentException("해당 사용자 No가 없습니다. No : " + memberNo));
                }

                JwtTokenResponseDto tokenDto = jwtTokenProvider.generateTokenDto(authentication, "SOCIAL");

//                // 4. RefreshToken 저장
//                RefreshToken refreshToken = RefreshToken.builder()
//                        .key(String.valueOf(memberNo))
//                        .value(tokenDto.getRefreshToken())
//                        .build();
//
//                refreshTokenRepository.save(refreshToken);

                DriverManager.println("SuccessHandler oAuth2User: " + defaultOAuth2User);
                response.sendRedirect(UriComponentsBuilder.fromUriString("http://localhost:3000")
                        .queryParam("bearer", tokenDto.getGrantType())
                        .queryParam("accessToken", tokenDto.getAccessToken())
//                        .queryParam("refreshToken", tokenDto.getRefreshToken())
                        .queryParam("expires", tokenDto.getAccessTokenExpires())
                        .queryParam("expiresDate", tokenDto.getAccessTokenExpiresDate())
                        .build().encode(StandardCharsets.UTF_8)
                        .toUriString());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

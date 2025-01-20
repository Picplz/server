package com.hm.picplz.domain.member.service;

import com.hm.picplz.domain.member.Repository.MemberRepository;
import com.hm.picplz.domain.member.dto.request.UpdateMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final String GEO_KEY = "members:locations";

    private final MemberRepository memberRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public void updateLocation(UpdateMemberRequest request) {
        redisTemplate.opsForGeo().add(GEO_KEY, new RedisGeoCommands.GeoLocation<>(
                request.getMemberId(), new Point(request.getLongitude(), request.getLatitude())));

        // TODO: role이 photographer인지 customer인지 파악 후 부가 정보로 추가 + 활동중 여부
    }
}

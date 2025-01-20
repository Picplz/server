package com.hm.picplz.domain.member.service;

import com.hm.picplz.domain.member.dto.request.UpdateMemberRequest;
import com.hm.picplz.domain.photographer.dto.PhotographerDto;
import com.hm.picplz.domain.photographer.helper.PhotographerHelper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands.GeoLocation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final String GEO_KEY = "members:locations";

    private final PhotographerHelper photographerHelper;
    private final RedisTemplate<String, Object> redisTemplate;

    public void updateLocation(UpdateMemberRequest request) {
        redisTemplate.opsForGeo().add(GEO_KEY, new RedisGeoCommands.GeoLocation<>(
                request.getMemberId(), new Point(request.getLongitude(), request.getLatitude())));

        // TODO: role이 photographer인지 customer인지 파악 후 부가 정보로 추가 + 활동중 여부
    }

    /**
     *
     * @param longitude 기준 경도
     * @param latitude 기준 위도
     * @param distance 반경 (단위: km)
     * @return 검색된 데이터 목록
     */
    public List<PhotographerDto.Card> findPhotographersWithinRadius(double longitude, double latitude, long distance) {
        Circle circle = new Circle(new Point(longitude, latitude), distance * 1000);    // 반경 (단위: m)

        // 검색 옵션 (거리 포함, 가까운 순)
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs
                .newGeoRadiusArgs()
                .includeDistance()
                .sortAscending();

        List<GeoResult<GeoLocation<Object>>> results = redisTemplate
                .opsForGeo()
                .radius(GEO_KEY, circle, args)
                .getContent();

        return photographerHelper.getPhotographerCardByMemberGeoInfo(results);
    }
}

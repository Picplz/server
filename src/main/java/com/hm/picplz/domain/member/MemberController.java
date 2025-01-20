package com.hm.picplz.domain.member;

import com.hm.picplz.domain.member.dto.request.UpdateMemberRequest;
import com.hm.picplz.domain.member.service.MemberService;
import com.hm.picplz.domain.photographer.dto.PhotographerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/members")
@Tag(name = "Member")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "위치 업데이트")
    @PostMapping(value = "/location")
    public void updateMemberLocation(
            @RequestBody UpdateMemberRequest updateMemberRequest
    ) {
        log.info("사용자 위치 업데이트");
        memberService.updateLocation(updateMemberRequest);
    }

    @Operation(summary = "주변 작가 불러오기")
    @GetMapping(value = "/location/nearby")
    public List<PhotographerDto.Card> loadNearbyPhotographers(
            @RequestParam double longitude,
            @RequestParam double latitude,
            @RequestParam long distance
    ) {
        log.info("주변 작가 불러오기");
        return memberService.findPhotographersWithinRadius(longitude, latitude, distance);
    }

}

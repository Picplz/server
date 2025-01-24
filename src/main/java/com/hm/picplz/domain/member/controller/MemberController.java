package com.hm.picplz.domain.member.controller;

import com.hm.picplz.domain.member.dto.request.UpdateMemberInfoRequest;
import com.hm.picplz.domain.member.dto.request.UpdateMemberLocationRequest;
import com.hm.picplz.domain.member.dto.response.MemberInfoResponse;
import com.hm.picplz.domain.member.service.MemberService;
import com.hm.picplz.domain.photographer.dto.PhotographerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/members")
@Tag(name = "Member")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원 닉네임, 프로필 사진 업데이트")
    @PatchMapping(value = "/info")
    public ResponseEntity<MemberInfoResponse> updateMemberInfo(@RequestBody UpdateMemberInfoRequest updateMemberInfoRequest) {
        return new ResponseEntity<>(memberService.updateMemberInfo(updateMemberInfoRequest), HttpStatus.OK);
    }

    @Operation(summary = "위치 업데이트")
    @PostMapping(value = "/location")
    public void updateMemberLocation(
            @RequestBody UpdateMemberLocationRequest updateMemberLocationRequest
    ) {
        log.info("사용자 위치 업데이트");
        memberService.updateLocation(updateMemberLocationRequest);
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

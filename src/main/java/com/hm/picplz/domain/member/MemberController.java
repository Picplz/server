package com.hm.picplz.domain.member;

import com.hm.picplz.domain.member.dto.request.UpdateMemberRequest;
import com.hm.picplz.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}

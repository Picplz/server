package com.hm.picplz.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateMemberRequest {

    @Schema(defaultValue = "1", description = "회원 아이디")
    @NotNull(message = "회원 아이디를 입력해주세요")
    private Long memberId;

    @Schema(defaultValue = "37.5665", description = "위도")
    @NotNull(message = "위도를 입력해주세요")
    private double latitude;

    @Schema(defaultValue = "126.9780", description = "경도")
    @NotNull(message = "경도를 입력해주세요")
    private double longitude;
}

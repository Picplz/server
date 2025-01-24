package com.hm.picplz.domain.member.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateMemberInfoRequest {

    public Long id;

    @NotNull
    @Size(max = 30)
    private String nickname;

    private String profileImage;
}


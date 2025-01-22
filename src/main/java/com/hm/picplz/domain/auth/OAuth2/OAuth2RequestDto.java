package com.hm.picplz.domain.auth.OAuth2;

import com.hm.picplz.domain.member.domain.Member;
import com.hm.picplz.domain.member.domain.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuth2RequestDto {

    private String attributeCode;
    private String name;
    private String provider;

    public Member toOAuth2() {
        return Member.builder()
                .name(name)
                .nickname("")
                .kakaoEmail("")
                .profileImage("")
                .provider(provider)
                .birth(null)
                .role(Role.GENERAL)
                .provider(provider)
                .attributeCode(attributeCode)
                .build();
    }}

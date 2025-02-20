package com.hm.picplz.domain.member.dto.response;

import com.hm.picplz.domain.member.domain.Member;
import com.hm.picplz.domain.member.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MemberInfoResponse {

    private Long id;
    private String name;
    private String nickname;
    private LocalDate birth;
    private Role role;
    private String kakaoEmail;
    private String profileImage;
    private String provider;
    private String attributeCode;

    @Builder
    public MemberInfoResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.birth = member.getBirth();
        this.role = member.getRole();
        this.kakaoEmail = member.getKakaoEmail();
        this.profileImage = member.getProfileImage();
        this.provider = member.getProvider();
        this.attributeCode = member.getAttributeCode();
    }
}

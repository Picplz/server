package com.hm.picplz.domain.member.domain;

import com.hm.picplz.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long id;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Size(max = 30)
    private String nickname;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @NotNull
    private String kakaoEmail;

    private String profileImage;

    private String provider;

    private String attributeCode;

    @Builder
    private Member(Long id, String name, String nickname, LocalDate birth, Role role, String kakaoEmail, String profileImage, String provider, String attributeCode) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.birth = birth;
        this.role = role;
        this.kakaoEmail = kakaoEmail;
        this.profileImage = profileImage;
        this.provider = provider;
        this.attributeCode = attributeCode;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public Member oAuthInfoUpdate(String memberName, String memberProvider) {
        this.name = memberName;
        this.provider = memberProvider;
        return this;
    }

    // factory method
}

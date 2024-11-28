package com.hm.picplz.domain.member.domain;

import com.hm.picplz.global.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Member extends BaseEntity {

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

    @NotNull
    private String kakaoEmail;

    private String profileImage;

    @Builder
    protected Member(Long id, String name, String nickname, LocalDate birth, String kakaoEmail,
        String profileImage) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.birth = birth;
        this.kakaoEmail = kakaoEmail;
        this.profileImage = profileImage;
    }

    // factory method
}

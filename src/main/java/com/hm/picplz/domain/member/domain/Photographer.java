package com.hm.picplz.domain.member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Photographer")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Photographer {

    @Id
    @EqualsAndHashCode.Include
    private ObjectId id;
    private String nickname;
    private String profileImage;
    private String area;            // 활동 지역 -> enum
    private Integer minPrice;       // 촬영 최소 가격
    private Integer maxPrice;       // 촬영 최대 가격
    private String camera;          // 촬영 장비 -> enum
    private String workField;       // 작업 분야 -> enum
    private String introduction;    // 소개글
    @DBRef
    private Member member;

    public Photographer(ObjectId id, String nickname, String profileImage, String area, Integer minPrice, Integer maxPrice, String camera, String workField, String introduction, Member member) {
        this.id = id;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.area = area;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.camera = camera;
        this.workField = workField;
        this.introduction = introduction;
        this.member = member;
    }
}

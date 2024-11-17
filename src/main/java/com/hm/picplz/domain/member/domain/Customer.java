package com.hm.picplz.domain.member.domain;

import java.util.Date;
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

@Document("Customer")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {

    @Id
    @EqualsAndHashCode.Include
    private ObjectId id;
    private String nickname;
    private String profileImage;
    private String area;    // 선호지역 -> enum 관리
    private String camera;  // 선호 사진 장비 -> enum 관리
    @DBRef
    private Member member;

    public Customer(ObjectId id, String nickname, String profileImage, String area, String camera) {
        this.id = id;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.area = area;
        this.camera = camera;
    }
}

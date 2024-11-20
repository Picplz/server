package com.hm.picplz.domain.member.domain;

import java.util.Date;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Member {

    @Id
    @EqualsAndHashCode.Include
    private ObjectId id;
    private String name;
    private Date birth;
    private String kakaoEmail;
    private String device;  // 회원 디바이스 정보
    private Boolean notificationYn; // 알림 허용 여부
    @DBRef
    private Customer customer;
    @DBRef
    private Photographer photographer;

    @Builder
    public Member(ObjectId id, String name, Date birth, String kakaoEmail, String device, Boolean notificationYn) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.kakaoEmail = kakaoEmail;
        this.device = device;
        this.notificationYn = notificationYn;
    }
}

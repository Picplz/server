package com.hm.picplz.domain.member.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Reservation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reservation {

    @Id
    @EqualsAndHashCode.Include
    private ObjectId id;
    private int price;
    private LocalDateTime reservationTime;
    private String place;   // 예약 장소
    private String camera;  // 촬영 장비 -> enum
    private String photoType;   // 사진 타입 -> enum
    private int photoNumber;    // 촬영 사진 장수
    @DBRef
    private Customer customer;
    @DBRef
    private Photographer photographer;

    @Builder
    public Reservation(ObjectId id, int price, LocalDateTime reservationTime, String place, String camera, String photoType, int photoNumber, Customer customer, Photographer photographer) {
        this.id = id;
        this.price = price;
        this.reservationTime = reservationTime;
        this.place = place;
        this.camera = camera;
        this.photoType = photoType;
        this.photoNumber = photoNumber;
        this.customer = customer;
        this.photographer = photographer;
    }
}

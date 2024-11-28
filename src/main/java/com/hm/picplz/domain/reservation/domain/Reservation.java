package com.hm.picplz.domain.reservation.domain;

import com.hm.picplz.global.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", updatable = false)
    private Long id;

    @NotNull
    @PositiveOrZero
    private int price;

    private LocalDateTime reservationTime;

    private String place;

    private String camera;

    private String photoType;   // enum

    @NotNull
    @Positive
    private String photoAmount;

    private String photoPurpose;    // enum

    @Builder
    private Reservation(Long id, int price, LocalDateTime reservationTime, String place,
        String camera,
        String photoType, String photoAmount, String photoPurpose) {
        this.id = id;
        this.price = price;
        this.reservationTime = reservationTime;
        this.place = place;
        this.camera = camera;
        this.photoType = photoType;
        this.photoAmount = photoAmount;
        this.photoPurpose = photoPurpose;
    }
}

package com.hm.picplz.domain.product.domain;

import com.hm.picplz.domain.photographer.domain.Photographer;
import com.hm.picplz.global.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShootProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shoot_product_id", updatable = false)
    private Long id;

    @NotNull
    private String name;

    private String description;

    @Positive
    @NotNull
    private int shootPrice;

    private int shootDuration;    // enum (ex. 10분 이하) 분단위

    @Positive
    @NotNull
    private int amount;

    @NotNull
    private boolean editedYn;   // enum

    @Positive
    @NotNull
    private int editPrice;

    private String otherDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;
}

package com.hm.picplz.domain.photographer.domain;

import com.hm.picplz.domain.member.domain.Member;
import com.hm.picplz.domain.product.domain.ShootProduct;
import com.hm.picplz.domain.review.domain.Review;
import com.hm.picplz.global.common.entity.BaseEntity;
import com.hm.picplz.global.common.entity.YesNo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photographer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photographer_id", updatable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    private String area;

    @NotNull
    @Positive
    private int minPrice;

    @NotNull
    @Positive
    private int maxPrice;

    @NotNull
    private String camera;

    private String introduction;

    private int period; // 1년 3개월 -> 15개월

    private YesNo active;  // Y/N

    private String instagram;

    @OneToMany(mappedBy = "photographer", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ShootProduct> shootProducts = new ArrayList<>();

    @OneToMany(mappedBy = "photographer", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "photographer", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<PhotoMood> photoMoods = new ArrayList<>();

    @OneToMany(mappedBy = "photographer", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Career> careers = new ArrayList<>();

    @Builder
    private Photographer(Long id, Member member, String area, int minPrice, int maxPrice, String camera, String introduction, int period, YesNo active, String instagram) {
        this.id = id;
        this.member = member;
        this.area = area;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.camera = camera;
        this.introduction = introduction;
        this.period = period;
        this.active = active;
        this.instagram = instagram;
    }
}

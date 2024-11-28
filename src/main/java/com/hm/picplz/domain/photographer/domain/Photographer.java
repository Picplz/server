package com.hm.picplz.domain.photographer.domain;

import com.hm.picplz.domain.member.domain.Member;
import com.hm.picplz.domain.portfolio.domain.PortfolioPhoto;
import com.hm.picplz.domain.product.domain.ShootProduct;
import com.hm.picplz.domain.review.domain.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
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
public class Photographer extends Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photographer_id", updatable = false)
    private Long id;

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

    private String workField;

    private String introduction;

    @Builder.Default
    @OneToMany(mappedBy = "photographer", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Hashtag> hashtags = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "photographer", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ShootProduct> shootProducts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "photographer", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Review> reviews = new ArrayList<>();
}

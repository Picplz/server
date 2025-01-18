package com.hm.picplz.domain.review.domain;

import com.hm.picplz.global.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewPhoto extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_photo_id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    private String image;

    private LocalDate uploadDate;

    private int order;

    @Builder
    public ReviewPhoto(Long id, Review review, String image, LocalDate uploadDate, int order) {
        this.id = id;
        this.review = review;
        this.image = image;
        this.uploadDate = uploadDate;
        this.order = order;
    }

    // factory method
}

package com.hm.picplz.domain.review.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hm.picplz.domain.member.domain.Member;
import com.hm.picplz.global.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_like_id", updatable = false)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "review_id")
    private Review review;

    @Builder
    public ReviewLike(Long id, Member member, Review review) {
        this.id = id;
        this.member = member;
        this.review = review;
    }

    // factory method
}

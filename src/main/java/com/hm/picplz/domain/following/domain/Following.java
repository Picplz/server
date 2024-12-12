package com.hm.picplz.domain.following.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hm.picplz.global.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Following extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "following_id", updatable = false)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "following_user_id", nullable = false)
    private Following following;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "follower_user_id", nullable = false)
    private Following follower;

    @Builder
    public Following(Long id, Following following, Following follower) {
        this.id = id;
        this.following = following;
        this.follower = follower;
    }
    // factory method
}

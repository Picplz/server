package com.hm.picplz.domain.photographer.domain;

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
public class PhotoMood extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_mood_id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    private String content;

    @Builder
    public PhotoMood(Long id, Photographer photographer, String content) {
        this.id = id;
        this.photographer = photographer;
        this.content = content;
    }

    // factory method
}

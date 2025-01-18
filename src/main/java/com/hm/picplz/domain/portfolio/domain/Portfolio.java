package com.hm.picplz.domain.portfolio.domain;

import com.hm.picplz.domain.photographer.domain.Photographer;
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
public class Portfolio extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    private String location;

    private LocalDate uploadDate;

    @Builder
    public Portfolio(Long id, Photographer photographer, String location, LocalDate uploadDate) {
        this.id = id;
        this.photographer = photographer;
        this.location = location;
        this.uploadDate = uploadDate;
    }

    // factory method
}

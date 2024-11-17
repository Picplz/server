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

@Document("Review")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Review {

    @Id
    @EqualsAndHashCode.Include
    private ObjectId id;
    private String content;
    private LocalDateTime createdAt;
    @DBRef
    private Customer customer;
    @DBRef
    private Photographer photographer;

    public Review(ObjectId id, String content, LocalDateTime createdAt, Customer customer, Photographer photographer) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.customer = customer;
        this.photographer = photographer;
    }
}

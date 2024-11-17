package com.hm.picplz.domain.member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Photo")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Photo {

    @Id
    @EqualsAndHashCode.Include
    private ObjectId id;
    private String image;   // 사진 데이터
    @DBRef
    private Photographer photographer;

    public Photo(ObjectId id, String image, Photographer photographer) {
        this.id = id;
        this.image = image;
        this.photographer = photographer;
    }
}

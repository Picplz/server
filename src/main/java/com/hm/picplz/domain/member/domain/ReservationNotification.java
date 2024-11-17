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

@Document("ReservationNotification")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReservationNotification {

    @Id
    @EqualsAndHashCode.Include
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime sendTime;
    private String type;    // 알림 타입 -> enum
    private LocalDateTime createdAt;    // 생성 시간
    @DBRef
    private Reservation reservation;

    public ReservationNotification(ObjectId id, String title, String content, LocalDateTime sendTime, String type, LocalDateTime createdAt, Reservation reservation) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.sendTime = sendTime;
        this.type = type;
        this.createdAt = createdAt;
        this.reservation = reservation;
    }
}

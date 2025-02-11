package com.hm.picplz.domain.member.exception;

import static org.springframework.http.HttpStatus.*;

import com.hm.picplz.global.error.BaseErrorCode;
import com.hm.picplz.global.error.ErrorReason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    /* Member */
    MEMBER_NOT_FOUND(BAD_REQUEST, "MEMBER_404_1", "해당 회원이 존재하지 않습니다.");

    private HttpStatus status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status.value(), code, reason);
    }
}

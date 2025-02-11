package com.hm.picplz.global.common.response;

import com.hm.picplz.global.error.ErrorReason;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonResponse {

    private LocalDateTime timestamp;
    private int statusCode;
    private String message;

    private CommonResponse(int statusCode, String message) {
        this.timestamp = LocalDateTime.now();
        this.statusCode = statusCode;
        this.message = message;
    }

    @Getter
    public static class SuccessResponse<T> extends CommonResponse {
        private T data;

        @Builder
        public SuccessResponse(int statusCode, String message, T data) {
            super(statusCode, message);
            this.data = data;
        }
    }

    @Getter
    public static class ErrorResponse extends CommonResponse {
        private String code;

        @Builder
        public ErrorResponse(int statusCode, String message, String code) {
            super(statusCode, message);
            this.code = code;
        }

        public static ErrorResponse from(ErrorReason errorReason) {
            return ErrorResponse.builder()
                    .statusCode(errorReason.getStatus())
                    .message(errorReason.getReason())
                    .code(errorReason.getCode())
                    .build();
        }
    }
}

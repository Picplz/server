package com.hm.picplz.domain.auth.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtTokenRequestDto {

    private String accessToken;
    private String refreshToken;
}

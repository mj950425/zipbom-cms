package com.zipbom.zipbom.Auth.dto;

import lombok.Getter;

@Getter
public class SignUpRequestDto {
    private String email;
    private String nickname;
    private String imageEncoding;
}

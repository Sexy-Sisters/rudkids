package com.rudkids.rudkids.domain.user.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpRequestDto {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;

    public SignUpRequestDto() {
    }

    @Builder
    public SignUpRequestDto(String email, String password, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}

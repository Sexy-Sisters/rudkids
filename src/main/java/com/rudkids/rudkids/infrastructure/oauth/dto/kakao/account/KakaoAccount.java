package com.rudkids.rudkids.infrastructure.oauth.dto.kakao.account;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class KakaoAccount {
    private String email;
    private String gender;

    public KakaoAccount(String email, String gender) {
        this.email = email;
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }
}

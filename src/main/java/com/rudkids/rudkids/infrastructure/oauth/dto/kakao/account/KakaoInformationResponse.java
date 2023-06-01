package com.rudkids.rudkids.infrastructure.oauth.dto.kakao.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class KakaoInformationResponse {

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @JsonProperty("properties")
    private Properties properties;

    public String getEmail() {
        return kakaoAccount.getEmail();
    }

    public String getNickname() {
        return properties.getNickname();
    }

    public String getGender() {
        return kakaoAccount.getGender().toUpperCase();
    }

    public String getProfileImage() {
        return properties.getProfileImage();
    }
}

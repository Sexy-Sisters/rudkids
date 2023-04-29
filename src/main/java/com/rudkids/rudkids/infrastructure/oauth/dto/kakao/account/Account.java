package com.rudkids.rudkids.infrastructure.oauth.dto.kakao.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Account {
    private Long id;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @JsonProperty("properties")
    private Properties properties;

    public Account() {
    }

    public Account(Long id, KakaoAccount kakaoAccount, Properties properties) {
        this.id = id;
        this.kakaoAccount = kakaoAccount;
        this.properties = properties;
    }

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

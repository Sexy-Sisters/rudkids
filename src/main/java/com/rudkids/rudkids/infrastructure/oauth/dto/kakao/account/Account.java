package com.rudkids.rudkids.infrastructure.oauth.dto.kakao.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Account {
    private Long id;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    public Account() {
    }

    public Account(Long id, KakaoAccount kakaoAccount) {
        this.id = id;
        this.kakaoAccount = kakaoAccount;
    }

    public String getEmail() {
        return kakaoAccount.getEmail();
    }

//    public String getNickname() {
//        return kakaoAccount.getProfileNickname();
//    }

//    public String getGender() {
//        return kakaoAccount.getGender();
//    }
}

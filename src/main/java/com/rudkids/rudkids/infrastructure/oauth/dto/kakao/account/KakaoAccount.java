package com.rudkids.rudkids.infrastructure.oauth.dto.kakao.account;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoAccount {
    private String email;
//    private Profile profile;
//    private String gender;

    public KakaoAccount() {
    }

    public KakaoAccount(String email) {
        this.email = email;
//        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

//    public String getProfileNickname() {
//        return profile.getNickname();
//    }


//    public String getGender() {
//        return gender.toUpperCase();
//    }
}

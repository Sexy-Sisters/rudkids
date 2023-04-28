package com.rudkids.rudkids.infrastructure.oauth.dto.kakao.account;

public class Profile {
    private String nickname;

    public Profile() {
    }

    public Profile(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}

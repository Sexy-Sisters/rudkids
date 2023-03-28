package com.rudkids.rudkids.common.fixtures;

import com.rudkids.rudkids.interfaces.auth.dto.AuthRequest;
import com.rudkids.rudkids.interfaces.auth.dto.AuthResponse;

public class AuthFixtures {

    public static final String GOOGLE_PROVIDER = "google";
    public static final String OAuth_로그인_링크 = "https://accounts.google.com/o/oauth2/v2/auth";
    public static final String REDIRECT_URI = "http://localhost:3000";
    public static final String MEMBER_인증_코드 = "member authorization code";
    public static final String 에세스_토큰 = "access";
    public static final String 리프래쉬_토큰 = "refresh";

    public static AuthRequest.Token USER_토큰_요청() {
        return new AuthRequest.Token(MEMBER_인증_코드, REDIRECT_URI);
    }

    public static AuthResponse.AccessAndRefreshToken USER_토큰_응답() {
        return new AuthResponse.AccessAndRefreshToken(에세스_토큰, 리프래쉬_토큰);
    }

    public static AuthRequest.RenewalToken USER_에세스_토큰_재발급_요청() {
        return new AuthRequest.RenewalToken(리프래쉬_토큰);
    }

    public static AuthResponse.AccessToken 에세스_토큰_재발급_응답() {
        return new AuthResponse.AccessToken(에세스_토큰);
    }
}

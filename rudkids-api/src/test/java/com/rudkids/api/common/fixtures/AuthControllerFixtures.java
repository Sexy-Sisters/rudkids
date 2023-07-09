package com.rudkids.api.common.fixtures;

import com.rudkids.core.auth.dto.AuthRequest;
import com.rudkids.core.auth.dto.AuthResponse;
import com.rudkids.core.auth.dto.AuthUser;

public class AuthControllerFixtures {

    public static final String GOOGLE_PROVIDER = "google";
    public static final String KAKAO_PROVIDER = "kakao";
    public static final AuthResponse.Uri OAuth_로그인_링크 = new AuthResponse.Uri("https://");
    public static final String REDIRECT_URI = "http://localhost:3000";
    public static final String MEMBER_인증_코드 = "member authorization code";
    public static final String 엑세스_토큰 = "access";
    public static final String 리프래쉬_토큰 = "refresh";

    public static AuthRequest.Token USER_토큰_요청() {
        return new AuthRequest.Token(MEMBER_인증_코드, REDIRECT_URI);
    }

    public static AuthUser.OAuth USER_AUTH_응답() {
        return AuthUser.OAuth.builder()
            .email("email")
            .name("name")
            .picture("https://")
            .build();
    }

    public static AuthResponse.AccessAndRefreshToken USER_토큰_응답() {
        return new AuthResponse.AccessAndRefreshToken(엑세스_토큰, 리프래쉬_토큰, false);
    }

    public static AuthRequest.RenewalToken USER_엑세스_토큰_재발급_요청() {
        return new AuthRequest.RenewalToken(리프래쉬_토큰);
    }

    public static AuthResponse.AccessToken 엑세스_토큰_재발급_응답() {
        return new AuthResponse.AccessToken(엑세스_토큰);
    }
}

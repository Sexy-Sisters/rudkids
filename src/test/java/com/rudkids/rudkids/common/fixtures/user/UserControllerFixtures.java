package com.rudkids.rudkids.common.fixtures.user;

import com.rudkids.rudkids.interfaces.user.dto.UserRequest;

public class UserControllerFixtures {

    public static final int 유저_나이 = 18;
    public static final String 유저_성별 = "MALE";
    public static final int 잘못된_유저_나이 = 1;
    public static final String 잘못된_유저_성별 = "Male";

    /* 헤더 정보 */
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    public static final String AUTHORIZATION_HEADER_VALUE = "Bearer aaaaaaaa.bbbbbbbb.cccccccc";

    public static UserRequest.SignUp USER_회원가입_요청() {
        return new UserRequest.SignUp(유저_나이, 유저_성별);
    }

    public static UserRequest.SignUp USER_잘못된_나이_회원가입_요청() {
        return new UserRequest.SignUp(잘못된_유저_나이, 유저_성별);
    }

    public static UserRequest.SignUp USER_잘못된_성별_회원가입_요청() {
        return new UserRequest.SignUp(유저_나이, 잘못된_유저_성별);
    }

    public static UserRequest.Update USER_정보_수정_요청() {
        return new UserRequest.Update(유저_나이, 유저_성별);
    }

    public static UserRequest.Update USER_잘못된_유저_나이_정보_수정_요청() {
        return new UserRequest.Update(잘못된_유저_나이, 유저_성별);
    }

    public static UserRequest.Update USER_잘못된_유저_성별_정보_수정_요청() {
        return new UserRequest.Update(유저_나이, 잘못된_유저_성별);
    }
}

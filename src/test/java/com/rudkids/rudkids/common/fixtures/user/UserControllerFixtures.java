package com.rudkids.rudkids.common.fixtures.user;

import com.rudkids.rudkids.domain.user.UserInfo;
import com.rudkids.rudkids.interfaces.user.dto.UserRequest;

import java.util.List;

public class UserControllerFixtures {
    public static final String USER_DEFAULT_URL = "/api/v1/user";
    private static final String USER_이름 = "남세";
    private static final String USER_폰번호 = "01029401509";
    private static final String USER_프로필_이미지 = "url";

    public static UserRequest.Update USER_수정_요청() {
        return new UserRequest.Update(USER_이름, USER_폰번호, "path", USER_프로필_이미지);
    }

    public static UserInfo.Main USER_정보_조회() {
        return UserInfo.Main.builder()
            .email("namse@gmail.com")
            .name(USER_이름)
            .gender("MALE")
            .age(19)
            .phoneNumber(USER_폰번호)
            .profileImage(USER_프로필_이미지)
            .build();
    }

    public static List<UserInfo.Addresses> USER_주소_정보들_조회() {
        return List.of(
            UserInfo.Addresses.builder()
                .receiverName(USER_이름)
                .receiverPhone(USER_폰번호)
                .receiverAddress1("부산시")
                .receiverAddress2("강서구 가락대로")
                .receiverZipCode("12324")
                .message("메세지")
                .build()
        );
    }
}

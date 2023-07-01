package com.rudkids.api.common.fixtures;

import com.rudkids.core.user.dto.UserRequest;
import com.rudkids.core.user.dto.UserResponse;

import java.util.List;

public class UserControllerFixtures {
    public static final String USER_DEFAULT_URL = "/api/v1/user";
    private static final String USER_이름 = "남세";
    private static final String USER_폰번호 = "01029401509";
    private static final String USER_프로필_이미지 = "url";

    public static UserRequest.Update USER_수정_요청() {
        return new UserRequest.Update(USER_이름, USER_폰번호, "path", USER_프로필_이미지);
    }

    public static UserResponse.Info USER_정보_조회() {
        return UserResponse.Info.builder()
            .email("namse@gmail.com")
            .name(USER_이름)
            .phoneNumber(USER_폰번호)
            .profileImage(USER_프로필_이미지)
            .build();
    }

    public static List<UserResponse.Address> USER_주소_정보들_조회() {
        return List.of(
            UserResponse.Address.builder()
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

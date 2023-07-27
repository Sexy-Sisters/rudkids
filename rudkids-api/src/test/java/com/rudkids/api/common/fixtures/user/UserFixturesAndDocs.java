package com.rudkids.api.common.fixtures.user;

import com.rudkids.core.community.dto.CommunityResponse;
import com.rudkids.core.image.dto.ImageResponse;
import com.rudkids.core.user.dto.UserRequest;
import com.rudkids.core.user.dto.UserResponse;

import java.util.List;
import java.util.UUID;

public class UserFixturesAndDocs {
    public static final String USER_DEFAULT_URL = "/api/v1/user";

    public static UserRequest.Update USER_수정_요청() {
        return new UserRequest.Update("남세", "path", "http://");
    }

    public static UserResponse.Info USER_정보_조회() {
        return UserResponse.Info.builder()
            .email("namse@gmail.com")
            .name("남세")
            .phoneNumber("01029401509")
            .profileImage(new ImageResponse.Info("http://", "http://"))
            .deliveringOrderCount(2)
            .boughtCollectionItemCount(1)
            .build();
    }

    public static List<CommunityResponse.Main> USER_커뮤니티글_응답() {
        return List.of(new CommunityResponse.Main("title", "writer", "image"));
    }

    public static List<UserResponse.Address> USER_주소_정보들_조회() {
        return List.of(
            UserResponse.Address.builder()
                .receiverName("남세")
                .receiverPhone("01029401509")
                .receiverAddress1("부산시")
                .receiverAddress2("강서구 가락대로")
                .receiverZipCode("12324")
                .message("메세지")
                .build()
        );
    }
}

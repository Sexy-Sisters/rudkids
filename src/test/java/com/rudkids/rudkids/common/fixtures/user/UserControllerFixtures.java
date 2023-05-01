package com.rudkids.rudkids.common.fixtures.user;

import com.rudkids.rudkids.interfaces.user.dto.UserRequest;

public class UserControllerFixtures {
    public static final String USER_DEFAULT_URL = "/api/v1/user";

    public static UserRequest.Update USER_수정_요청() {
        return new UserRequest.Update("남세", "01012345678", "path", "url");
    }
}

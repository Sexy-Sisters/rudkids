package com.rudkids.rudkids.common.fixtures.admin;

import com.rudkids.rudkids.domain.admin.AdminInfo;
import com.rudkids.rudkids.domain.user.domain.RoleType;
import com.rudkids.rudkids.interfaces.admin.dto.AdminRequest;

import java.util.List;
import java.util.UUID;

public class AdminControllerFixtures {

    public static final String ADMIN_PRODUCT_DEFAULT_URL = "/api/v1/admin/product";
    public static final String ADMIN_USER_DEFAULT_URL = "/api/v1/admin/user";
    public static final String ADMIN_MAGAZINE_DEFAULT_URL = "/api/v1/admin/magazine";
    public static final String USER_EMAIL = "test@gmail.com";
    public static final UUID USER_ID = UUID.randomUUID();

    public static AdminRequest.ChangeUserRole 유저_권한_변경_요청() {
        return new AdminRequest.ChangeUserRole(RoleType.PARTNER);
    }

    public static List<AdminInfo.User> 유저_정보_INFO_응답() {
        return List.of(new AdminInfo.User("email@gmail.com",
            "name",
                18,
                "MALE",
                "01012345678",
                "PARTNER")
        );
    }
}

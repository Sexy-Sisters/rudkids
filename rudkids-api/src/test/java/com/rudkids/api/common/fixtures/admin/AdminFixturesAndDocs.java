package com.rudkids.api.common.fixtures.admin;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.admin.dto.AdminResponse;

import java.util.List;
import java.util.UUID;

public class AdminFixturesAndDocs {

    public static final String ADMIN_PRODUCT_DEFAULT_URL = "/api/v1/admin/product";
    public static final String ADMIN_USER_DEFAULT_URL = "/api/v1/admin/user";
    public static final String ADMIN_ITEM_DEFAULT_URL = "/api/v1/admin/item";
    public static final String ADMIN_ORDER_DEFAULT_URL = "/api/v1/admin/order";
    public static final String USER_EMAIL = "test@gmail.com";
    public static final UUID USER_ID = UUID.randomUUID();
    public static final UUID PRODUCT_ID = UUID.randomUUID();
    public static final UUID ORDER_ID = UUID.randomUUID();

    public static AdminRequest.ChangeUserRole 유저_권한_변경_요청() {
        return new AdminRequest.ChangeUserRole("PARTNER");
    }

    public static List<AdminResponse.UserInfo> 유저_검색_응답() {
        return List.of(new AdminResponse.UserInfo(
            "email@gmail.com",
                "name",
                "01012345678",
                "url",
                "PARTNER"
            )
        );
    }
}

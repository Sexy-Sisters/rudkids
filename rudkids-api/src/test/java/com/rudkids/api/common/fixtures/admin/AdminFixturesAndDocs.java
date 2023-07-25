package com.rudkids.api.common.fixtures.admin;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.admin.dto.AdminResponse;
import com.rudkids.core.image.dto.ImageRequest;
import com.rudkids.core.item.domain.LimitType;
import com.rudkids.core.order.dto.OrderItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

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

    public static AdminRequest.DeliveryTrackingNumber 송장번호_등록_요청() {
        return new AdminRequest.DeliveryTrackingNumber("deliveryTrackingNumber");
    }

    public static AdminRequest.CreateProduct 프로덕트_생성_요청() {
        return new AdminRequest.CreateProduct(
            "제목",
            "설명",
            new ImageRequest.Create("image", "image.jpg"),
            new ImageRequest.Create("image", "image.jpg"),
            List.of(
                new AdminRequest.BannerImage("image", "image.jpg", 1)
            ),
            new ImageRequest.Create("image", "image.jpg")
        );
    }

    public static AdminRequest.ChangeProductStatus PRODUCT_상태_변경_요청() {
        return new AdminRequest.ChangeProductStatus("CLOSED");
    }

    public static AdminRequest.UpdateProduct PRODUCT_수정_요청() {
        return new AdminRequest.UpdateProduct(
            "프로덕트 제목",
            "설명",
            new ImageRequest.Create("image", "image.jpg"),
            new ImageRequest.Create("image", "image.jpg"),
            List.of(
                new AdminRequest.BannerImage("image", "image.jpg", 1)
            ),
            new ImageRequest.Create("image", "image.jpg")
        );
    }

    public static AdminRequest.CreateItem ITEM_등록_요청() {
        return AdminRequest.CreateItem.builder()
            .enName("enName")
            .koName("koName")
            .itemBio("bio")
            .price(9000)
            .quantity(800)
            .limitType(LimitType.NORMAL)
            .images(List.of(new AdminRequest.Image("path", "url", 1)))
            .itemOptionGroupInfoList(List.of(itemOptionGroup_사이즈))
            .grayImage(new ImageRequest.Create("path", "url"))
            .build();
    }

    private static final AdminRequest.ItemOptionGroup itemOptionGroup_사이즈 = AdminRequest.ItemOptionGroup.builder()
        .itemOptionGroupName("사이즈")
        .itemOptionInfoList(List.of(
            AdminRequest.ItemOption.builder()
                .itemOptionName("S")
                .itemOptionPrice(0)
                .ordering(1)
                .build(),
            AdminRequest.ItemOption.builder()
                .itemOptionName("M")
                .itemOptionPrice(0)
                .ordering(2)
                .build(),
            AdminRequest.ItemOption.builder()
                .itemOptionName("L")
                .itemOptionPrice(1000)
                .ordering(3)
                .build()
        ))
        .ordering(1)
        .build();

    public static AdminRequest.ChangeItemStatus ITEM_상태_변경_요청() {
        return new AdminRequest.ChangeItemStatus("SOLD_OUT");
    }

    public static AdminRequest.UpdateItem ITEM_수정_요청() {
        return AdminRequest.UpdateItem.builder()
            .enName("enName")
            .koName("koName")
            .itemBio("bio")
            .price(90000)
            .quantity(800)
            .limitType(LimitType.NORMAL)
            .images(List.of(new AdminRequest.Image("image", "image.jpg", 1)))
            .itemOptionGroupInfoList(List.of(itemOptionGroup_사이즈))
            .grayImage(new ImageRequest.Create("path", "url"))
            .build();
    }

    public static Page<AdminResponse.OrderInfo> ORDER_전체_조회_응답() {
        return new PageImpl<>(List.of(
            new AdminResponse.OrderInfo(
                UUID.randomUUID(),
                "주문자",
                9000,
                List.of(new OrderItemResponse("imageUrl", "아이스크림", 1, 1000)),
                "주문완료",
                "2023.07.31",
                "124124-4534",
                "배송완료"
        )));
    }

    public static AdminResponse.OrderDetail ORDER_조회_응답() {
        return new AdminResponse.OrderDetail(
            List.of(new OrderItemResponse("imageUrl", "아이스크림", 1, 1000)),
            "받는사람",
            "받는 주소",
            "주문완료",
            "배송완료",
            "12345-6789"
        );
    }

    public static List<AdminResponse.UserSearchInfo> 유저_검색_응답() {
        return List.of(new AdminResponse.UserSearchInfo(
                "email@gmail.com",
                "name",
                "01012345678",
                "url",
                "PARTNER"
            )
        );
    }
}

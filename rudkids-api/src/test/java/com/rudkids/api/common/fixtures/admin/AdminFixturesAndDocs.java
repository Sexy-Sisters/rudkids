package com.rudkids.api.common.fixtures.admin;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.admin.dto.AdminResponse;
import com.rudkids.core.image.dto.ImageRequest;
import com.rudkids.core.item.domain.LimitType;
import com.rudkids.core.order.domain.OrderStatus;
import com.rudkids.core.order.dto.OrderItemResponse;
import com.rudkids.core.order.dto.OrderResponse;
import com.rudkids.core.product.dto.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.ZonedDateTime;
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
                new AdminRequest.CreateBannerImage("image", "image.jpg", 1)
            )
        );
    }

    public static ProductRequest.ChangeStatus PRODUCT_상태_변경_요청() {
        return new ProductRequest.ChangeStatus("CLOSED");
    }

    public static ProductRequest.Update PRODUCT_수정_요청() {
        return new ProductRequest.Update(
            "프로덕트 제목",
            "설명",
            new ImageRequest.Create("image", "image.jpg"),
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
            .images(List.of(new AdminRequest.CreateImage("path", "url", 1)))
            .itemOptionGroupList(List.of(itemOptionGroup_사이즈))
            .build();
    }

    private static final AdminRequest.CreateItemOptionGroup itemOptionGroup_사이즈 = AdminRequest.CreateItemOptionGroup.builder()
        .itemOptionGroupName("사이즈")
        .itemOptionList(List.of(
            AdminRequest.CreateItemOption.builder()
                .itemOptionName("S")
                .itemOptionPrice(0)
                .ordering(1)
                .build(),
            AdminRequest.CreateItemOption.builder()
                .itemOptionName("M")
                .itemOptionPrice(0)
                .ordering(2)
                .build(),
            AdminRequest.CreateItemOption.builder()
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
            .images(List.of(new ImageRequest.Create("image", "image.jpg")))
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
                new AdminResponse.OrderDeliveryInfo(
                    "받는사람 이름",
                    "받는 주소",
                    "배송중",
                    "123456-1234567"
                )
        )));
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

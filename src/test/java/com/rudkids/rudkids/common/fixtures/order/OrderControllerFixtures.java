package com.rudkids.rudkids.common.fixtures.order;

import com.rudkids.rudkids.domain.order.domain.PayMethod;
import com.rudkids.rudkids.interfaces.order.dto.OrderRequest;

import java.util.List;
import java.util.UUID;

public class OrderControllerFixtures {
    public static final String ORDER_DEFAULT_URL = "/api/v1/order";

    public static OrderRequest.Register ORDER_주문_요청() {
        return OrderRequest.Register.builder()
            .receiverName("이규진")
            .receiverPhone("010-5476-5574")
            .receiverAddress1("부산시 사하구 윤공단로123")
            .receiverAddress2("나는 몰라용~")
            .receiverZipcode("494999")
            .etcMessage("나는 2024년 총 매출 35억을 달성했고 다낭으로 여행왔다. 나는 2024년 페라리를 샀다.")
            .payMethod(PayMethod.TOSS)
            .orderItemList(List.of(
                ORDER_ITEM_주문_요청()
            ))
            .build();
    }

    public static OrderRequest.RegisterOrderItem ORDER_ITEM_주문_요청() {
        return OrderRequest.RegisterOrderItem.builder()
            .itemId(UUID.randomUUID())
            .orderCount(5)
            .orderItemOptionGroupList(List.of(
                ORDER_ITEM_OPTION_GROUP_주문_요청()
            ))
            .build();
    }

    public static OrderRequest.RegisterOrderItemOptionGroup ORDER_ITEM_OPTION_GROUP_주문_요청() {
        return OrderRequest.RegisterOrderItemOptionGroup.builder()
            .ordering(1)
            .itemOptionGroupName("약효지속시간")
            .orderItemOption(ORDER_ITEM_OPTION_주문_요청())
            .build();
    }

    public static OrderRequest.RegisterOrderItemOption ORDER_ITEM_OPTION_주문_요청() {
        return OrderRequest.RegisterOrderItemOption.builder()
            .ordering(1)
            .itemOptionName("5분")
            .itemOptionPrice(500)
            .build();
    }
}

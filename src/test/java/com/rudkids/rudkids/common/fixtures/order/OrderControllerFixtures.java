package com.rudkids.rudkids.common.fixtures.order;

import com.rudkids.rudkids.domain.order.OrderStore;
import com.rudkids.rudkids.domain.order.domain.OrderStatus;
import com.rudkids.rudkids.domain.order.domain.PayMethod;
import com.rudkids.rudkids.interfaces.order.dto.OrderRequest;

import java.util.List;
import java.util.UUID;

public class OrderControllerFixtures {

    public static final String ORDER_DEFAULT_URL = "/api/v1/order";
    public static final UUID orderId = UUID.randomUUID();
    public static final UUID cartId = UUID.randomUUID();

    public static OrderRequest.Register ORDER_주문_요청() {
        return OrderRequest.Register.builder()
            .receiverName("이규진")
            .receiverPhone("010-5476-5574")
            .receiverAddress1("부산시 사하구 윤공단로123")
            .receiverAddress2("나는 몰라용~")
            .receiverZipcode("494999")
            .etcMessage("나는 2024년 총 매출 35억을 달성했고 다낭으로 여행왔다. 나는 2024년 페라리를 샀다.")
            .payMethod(PayMethod.TOSS)
            .build();
    }

    public static OrderRequest.UpdateDeliveryFragment ORDER_배송_정보_수정_요청() {
        return OrderRequest.UpdateDeliveryFragment.builder()
            .receiverName("이규진")
            .receiverPhone("010-5476-5574")
            .receiverAddress1("부산시 사하구 윤공단로123")
            .receiverAddress2("나는 몰라용~")
            .receiverZipcode("494999")
            .etcMessage("나는 2024년 총 매출 35억을 달성했고 다낭으로 여행왔다. 나는 2024년 페라리를 샀다.")
            .build();
    }


    public static OrderRequest.ChangeStatus ORDER_상태변경_요청() {
        return new OrderRequest.ChangeStatus(OrderStatus.DELIVERY_COMPLETE);
    }
}

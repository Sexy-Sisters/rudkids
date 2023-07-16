package com.rudkids.api.common.fixtures.order;

import com.rudkids.core.delivery.dto.DeliveryResponse;
import com.rudkids.core.order.domain.OrderStatus;
import com.rudkids.core.order.dto.OrderItemResponse;
import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.dto.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static com.rudkids.api.common.ControllerTest.pageResponseFieldsWith;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class OrderFixturesAndDocs {

    public static final String ORDER_DEFAULT_URL = "/api/v1/order";
    public static final UUID orderId = UUID.randomUUID();
    public static final OrderStatus orderStatus = OrderStatus.ORDER;
    private static final String paymentMethod = "TOSS";
    private static final ZonedDateTime createdAt = ZonedDateTime.now();
    private static final UUID deliveryId = UUID.randomUUID();

    private static final String receiverName = "이규진";
    private static final String receiverPhone = "010-5476-5574";
    private static final String receiverAddress = "부산시 사하구 윤공단로123";
    private static final String receiverextraAddress = "나는 몰라용~";
    private static final String receiverZipcode = "494999";
    private static final String etcMessage = "나는 2024년 총 매출 35억을 달성했고 다낭으로 여행왔다. 나는 2024년 페라리를 샀다.";

    public static OrderRequest.Create ORDER_주문_요청() {
        return new OrderRequest.Create(deliveryId, paymentMethod);
    }

    public static OrderRequest.ChangeStatus ORDER_상태변경_요청() {
        return new OrderRequest.ChangeStatus("DELIVERY_COMPLETE");
    }

    public static OrderResponse.Create ORDER_생성_응답() {
        return new OrderResponse.Create(orderId);
    }

    public static OrderResponse.Detail ORDER_상세조회_응답() {
        var delivery = new OrderResponse.DetailDelivery(receiverName, receiverPhone, receiverAddress, etcMessage);

        return OrderResponse.Detail.builder()
            .orderId(orderId)
            .createdAt(createdAt)
            .orderStatus(orderStatus)
            .orderItems(ORDER_ITEM_응답())
            .delivery(delivery)
            .paymentMethod(paymentMethod)
            .build();
    }

    public static Page<OrderResponse.Main> ORDER_전체_조회_INFO() {
        return new PageImpl<>(List.of(
            new OrderResponse.Main(orderId, createdAt, orderStatus, ORDER_ITEM_응답())
        ));
    }

    public static List<OrderItemResponse> ORDER_ITEM_응답() {
        return List.of(new OrderItemResponse("imageUrl", "아이스크림", 1, 1000));
    }

    public static List<OrderResponse.Main> ORDER_주문내역_조회_INFO() {
        return List.of(new OrderResponse.Main(orderId, createdAt, orderStatus, ORDER_ITEM_응답()));
    }

    public static List<FieldDescriptor> ORDER_상세조회_응답_필드() {
        return List.of(fieldWithPath("orderId")
                .type(JsonFieldType.STRING)
                .description("주문 ID"),

            fieldWithPath("createdAt")
                .type(JsonFieldType.STRING)
                .description("주문 날짜"),

            fieldWithPath("orderStatus")
                .type(JsonFieldType.STRING)
                .description("주문 상태"),

            fieldWithPath("orderItems")
                .type(JsonFieldType.ARRAY)
                .description("주문한 상품들"),

            fieldWithPath("orderItems[].imageUrl")
                .type(JsonFieldType.STRING)
                .description("주문한 상품 이미지 url"),

            fieldWithPath("orderItems[].name")
                .type(JsonFieldType.STRING)
                .description("주문한 상품 이름"),

            fieldWithPath("orderItems[].amount")
                .type(JsonFieldType.NUMBER)
                .description("주문한 상품 개수"),

            fieldWithPath("orderItems[].price")
                .type(JsonFieldType.NUMBER)
                .description("주문한 상품 가격"),

            fieldWithPath("delivery.receiverName")
                .type(JsonFieldType.STRING)
                .description("수신자"),

            fieldWithPath("delivery.receiverPhone")
                .type(JsonFieldType.STRING)
                .description("전화번호"),

            fieldWithPath("delivery.receivedAddress")
                .type(JsonFieldType.STRING)
                .description("주소"),

            fieldWithPath("delivery.message")
                .type(JsonFieldType.STRING)
                .description("배송시 요청사항"),

            fieldWithPath("paymentMethod")
                .type(JsonFieldType.STRING)
                .description("결제수단")
        );
    }

    public static List<FieldDescriptor> ORDER_주문내역_조회_응답_필드() {
        return List.of(
            fieldWithPath("[].orderId")
                .type(JsonFieldType.STRING)
                .description("주문 ID"),

            fieldWithPath("[].createdAt")
                .type(JsonFieldType.STRING)
                .description("주문한 시간"),

            fieldWithPath("[].orderStatus")
                .type(JsonFieldType.STRING)
                .description("주문 상태"),

            fieldWithPath("[].orderItems")
                .type(JsonFieldType.ARRAY)
                .description("주문한 상품들"),

            fieldWithPath("[].orderItems[]imageUrl")
                .type(JsonFieldType.STRING)
                .description("주문한 상품 이미지 url"),

            fieldWithPath("[].orderItems[]name")
                .type(JsonFieldType.STRING)
                .description("주문한 상품 이름"),

            fieldWithPath("[].orderItems[]amount")
                .type(JsonFieldType.NUMBER)
                .description("주문한 상품 개수"),

            fieldWithPath("[].orderItems[]price")
                .type(JsonFieldType.NUMBER)
                .description("주문한 상품 가격")
        );
    }

    public static List<FieldDescriptor> ORDER_전체_주문_조회_응답_필드() {
        return pageResponseFieldsWith(
            List.of(
                fieldWithPath("content.[].orderId")
                    .type(JsonFieldType.STRING)
                    .description("주문 ID"),

                fieldWithPath("content.[].createdAt")
                    .type(JsonFieldType.STRING)
                    .description("주문한 시간"),

                fieldWithPath("content.[].orderStatus")
                    .type(JsonFieldType.STRING)
                    .description("주문 상태"),

                fieldWithPath("content.[].orderItems")
                    .type(JsonFieldType.ARRAY)
                    .description("주문한 상품들"),

                fieldWithPath("content.[].orderItems[]imageUrl")
                    .type(JsonFieldType.STRING)
                    .description("주문한 상품 이미지 url"),

                fieldWithPath("content.[].orderItems[]name")
                    .type(JsonFieldType.STRING)
                    .description("주문한 상품 이름"),

                fieldWithPath("content.[].orderItems[]amount")
                    .type(JsonFieldType.NUMBER)
                    .description("주문한 상품 개수"),

                fieldWithPath("content.[].orderItems[]price")
                    .type(JsonFieldType.NUMBER)
                    .description("주문한 상품 가격")
            )
        );
    }
}

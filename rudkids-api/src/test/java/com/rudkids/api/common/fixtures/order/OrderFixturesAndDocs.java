package com.rudkids.api.common.fixtures.order;

import com.rudkids.core.order.dto.OrderItemResponse;
import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.dto.OrderResponse;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;
import java.util.UUID;

import static com.rudkids.api.common.ControllerTest.pageResponseFieldsWith;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class OrderFixturesAndDocs {

    public static final String ORDER_DEFAULT_URL = "/api/v1/order";
    public static final UUID orderId = UUID.randomUUID();
    public static final String orderStatus = "주문완료";
    private static final String paymentMethod = "TOSS";
    private static final UUID deliveryId = UUID.randomUUID();

    private static final String receiverName = "이규진";
    private static final String receiverPhone = "010-5476-5574";
    private static final String receiverAddress = "부산시 사하구 윤공단로123";
    private static final String etcMessage = "나는 2024년 총 매출 35억을 달성했고 다낭으로 여행왔다. 나는 2024년 페라리를 샀다.";

    public static OrderRequest.OrderAndPayment ORDER_주문_요청() {
        return new OrderRequest.OrderAndPayment("paymentKey", "orderId", 3000);
    }

    public static OrderRequest.PaymentCancel ORDER_취소_요청() {
        return new OrderRequest.PaymentCancel("취소하는 이유", "12445", "한국은행", "남세원");
    }

    public static OrderResponse.Id ORDER_ID_응답() {
        return new OrderResponse.Id(orderId);
    }


    public static OrderResponse.Detail ORDER_상세조회_응답() {
        var delivery = new OrderResponse.DetailDelivery(receiverName, receiverPhone, receiverAddress, etcMessage);

        return OrderResponse.Detail.builder()
            .orderId(orderId)
            .createdAt("2023.07.31")
            .orderStatus(orderStatus)
            .orderItems(ORDER_ITEM_응답())
            .delivery(delivery)
            .paymentMethod(paymentMethod)
            .bankName("bankname")
            .accountNumber("accountNumber")
            .customerName("holder name")
            .build();
    }

    public static List<OrderItemResponse> ORDER_ITEM_응답() {
        return List.of(new OrderItemResponse("imageUrl", "아이스크림", 1, 1000));
    }

    public static List<OrderResponse.Main> ORDER_주문내역_조회_INFO() {
        return List.of(new OrderResponse.Main(orderId, "2023.07.31", orderStatus, ORDER_ITEM_응답()));
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
                .description("결제수단"),

            fieldWithPath("bankName")
                .type(JsonFieldType.STRING)
                .description("은행 이름"),

            fieldWithPath("accountNumber")
                .type(JsonFieldType.STRING)
                .description("계좌번호"),

            fieldWithPath("customerName")
                .type(JsonFieldType.STRING)
                .description("계좌 발급한 고객이름")
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

                fieldWithPath("content.[].customerName")
                    .type(JsonFieldType.STRING)
                    .description("주문자 이름"),

                fieldWithPath("content.[].amount")
                    .type(JsonFieldType.NUMBER)
                    .description("주문 총 가격"),

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
                    .description("주문한 상품 가격"),

                fieldWithPath("content.[].orderStatus")
                    .type(JsonFieldType.STRING)
                    .description("주문 상태"),

                fieldWithPath("content.[].createdAt")
                    .type(JsonFieldType.STRING)
                    .description("주문한 시간"),

                fieldWithPath("content.[].deliveryStatus")
                    .type(JsonFieldType.STRING)
                    .description("주문 배송 상태"),

                fieldWithPath("content.[].deliveryTrackingNumber")
                    .type(JsonFieldType.STRING)
                    .description("주문 배송 송장번호")
            )
        );
    }
}

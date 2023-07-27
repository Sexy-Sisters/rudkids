package com.rudkids.api.order;

import com.rudkids.api.common.ControllerTest;
import com.rudkids.core.order.exception.OrderDeliverNotReadyException;
import com.rudkids.core.order.exception.*;
import com.rudkids.core.order.exception.PaymentCancelFailException;
import com.rudkids.core.user.exception.DifferentUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.api.common.fixtures.order.OrderFixturesAndDocs.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest extends ControllerTest {

    @Nested
    @DisplayName("주문을 요청하면 결제를 한다")
    class order {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(orderService.order(any(), any()))
                .willReturn(ORDER_ID_응답());

            mockMvc.perform(post(ORDER_DEFAULT_URL)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ORDER_주문_요청())))
                .andDo(print())
                .andDo(document("order/order",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    requestFields(
                        fieldWithPath("paymentKey")
                            .type(JsonFieldType.STRING)
                            .description("결제 key"),

                        fieldWithPath("orderId")
                            .type(JsonFieldType.STRING)
                            .description("주문 id"),

                        fieldWithPath("amount")
                            .type(JsonFieldType.NUMBER)
                            .description("총 가격")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 결제방법")
        void fail2() throws Exception {
            doThrow(new PaymentMethodNotFoundException())
                .when(orderService)
                .order(any(), any());

            mockMvc.perform(post(ORDER_DEFAULT_URL)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ORDER_주문_요청())))
                .andDo(print())
                .andDo(document("order/order/fail/notFound/payMethod",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    requestFields(
                        fieldWithPath("paymentKey")
                            .type(JsonFieldType.STRING)
                            .description("결제 key"),

                        fieldWithPath("orderId")
                            .type(JsonFieldType.STRING)
                            .description("주문 id"),

                        fieldWithPath("amount")
                            .type(JsonFieldType.NUMBER)
                            .description("총 가격")
                    )
                ))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("주문을 상세조회한다")
    class get {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(orderService.get(any()))
                .willReturn(ORDER_상세조회_응답());

            mockMvc.perform(get(ORDER_DEFAULT_URL + "/{id}", orderId))
                .andDo(print())
                .andDo(document("order/get",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                            parameterWithName("id")
                                .description("주문 id")
                        ),
                        responseFields(ORDER_상세조회_응답_필드())
                    )
                )
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 주문내역")
        void fail() throws Exception {
            doThrow(new OrderNotFoundException())
                .when(orderService)
                .get(any());

            mockMvc.perform(get(ORDER_DEFAULT_URL + "/{id}", orderId))
                .andDo(print())
                .andDo(document("order/get/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    pathParameters(
                        parameterWithName("id")
                            .description("존재하지 않는 주문 id")
                    )
                ))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("주문내역 리스트를 반환한다")
    class getAll {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(orderService.getAll(any()))
                .willReturn(ORDER_주문내역_조회_INFO());

            mockMvc.perform(get(ORDER_DEFAULT_URL)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                )
                .andDo(print())
                .andDo(document("order/getAll",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                            headerWithName("Authorization")
                                .description("JWT Access Token")
                        ),
                        responseFields(ORDER_주문내역_조회_응답_필드())
                    )
                )
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("주문을 취소한다")
    class cancel {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(orderService.cancel(any(), any(), any()))
                .willReturn(ORDER_ID_응답());

            mockMvc.perform(post(ORDER_DEFAULT_URL + "/{id}", orderId)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ORDER_취소_요청())))
                .andDo(print())
                .andDo(document("order/cancel",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("주문 id")
                    ),
                    requestFields(
                        fieldWithPath("cancelReason")
                            .type(JsonFieldType.STRING)
                            .description("취소하는 이유"),

                        fieldWithPath("refundAccountNumber")
                            .type(JsonFieldType.STRING)
                            .description("환불계좌 번호"),

                        fieldWithPath("bankName")
                            .type(JsonFieldType.STRING)
                            .description("은행 이름"),

                        fieldWithPath("refundAccountHolderName")
                            .type(JsonFieldType.STRING)
                            .description("환불 예금주명")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 주문내역")
        void fail() throws Exception {
            doThrow(new OrderNotFoundException())
                .when(orderService)
                .cancel(any(), any(), any());

            mockMvc.perform(post(ORDER_DEFAULT_URL + "/{id}", orderId)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ORDER_취소_요청())))
                .andDo(print())
                .andDo(document("order/cancel/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("존재하지 않는 주문 id")
                    ),
                    requestFields(
                        fieldWithPath("cancelReason")
                            .type(JsonFieldType.STRING)
                            .description("취소하는 이유"),

                        fieldWithPath("refundAccountNumber")
                            .type(JsonFieldType.STRING)
                            .description("환불계좌 번호"),

                        fieldWithPath("bankName")
                            .type(JsonFieldType.STRING)
                            .description("은행 이름"),

                        fieldWithPath("refundAccountHolderName")
                            .type(JsonFieldType.STRING)
                            .description("환불 예금주명")
                    )
                ))
                .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("실패: 다른유저의 주문내역")
        void fail2() throws Exception {
            doThrow(new DifferentUserException())
                .when(orderService)
                .cancel(any(), any(), any());

            mockMvc.perform(post(ORDER_DEFAULT_URL + "/{id}", orderId)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ORDER_취소_요청())))
                .andDo(print())
                .andDo(document("order/cancel/fail/forbidden",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("다른 유저의 JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("주문 id")
                    ),
                    requestFields(
                        fieldWithPath("cancelReason")
                            .type(JsonFieldType.STRING)
                            .description("취소하는 이유"),

                        fieldWithPath("refundAccountNumber")
                            .type(JsonFieldType.STRING)
                            .description("환불계좌 번호"),

                        fieldWithPath("bankName")
                            .type(JsonFieldType.STRING)
                            .description("은행 이름"),

                        fieldWithPath("refundAccountHolderName")
                            .type(JsonFieldType.STRING)
                            .description("환불 예금주명")
                    )
                ))
                .andExpect(status().isForbidden());
        }

        @Test
        @DisplayName("실패: 이미 배송완료된 주문내역")
        void fail3() throws Exception {
            doThrow(new OrderDeliverNotReadyException())
                .when(orderService)
                .cancel(any(), any(), any());

            mockMvc.perform(post(ORDER_DEFAULT_URL + "/{id}", orderId)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ORDER_취소_요청())))
                .andDo(print())
                .andDo(document("order/cancel/fail/conflict",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("주문 id")
                    ),
                    requestFields(
                        fieldWithPath("cancelReason")
                            .type(JsonFieldType.STRING)
                            .description("취소하는 이유"),

                        fieldWithPath("refundAccountNumber")
                            .type(JsonFieldType.STRING)
                            .description("환불계좌 번호"),

                        fieldWithPath("bankName")
                            .type(JsonFieldType.STRING)
                            .description("은행 이름"),

                        fieldWithPath("refundAccountHolderName")
                            .type(JsonFieldType.STRING)
                            .description("환불 예금주명")
                    )
                ))
                .andExpect(status().isConflict());
        }

        @Test
        @DisplayName("실패: 외부 API 통신")
        void fail4() throws Exception {
            doThrow(new PaymentCancelFailException())
                .when(orderService)
                .cancel(any(), any(), any());

            mockMvc.perform(post(ORDER_DEFAULT_URL + "/{id}", orderId)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ORDER_취소_요청())))
                .andDo(print())
                .andDo(document("order/cancel/fail/internal",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("주문 id")
                    ),
                    requestFields(
                        fieldWithPath("cancelReason")
                            .type(JsonFieldType.STRING)
                            .description("취소하는 이유"),

                        fieldWithPath("refundAccountNumber")
                            .type(JsonFieldType.STRING)
                            .description("환불계좌 번호"),

                        fieldWithPath("bankName")
                            .type(JsonFieldType.STRING)
                            .description("은행 이름"),

                        fieldWithPath("refundAccountHolderName")
                            .type(JsonFieldType.STRING)
                            .description("환불 예금주명")
                    )
                ))
                .andExpect(status().isInternalServerError());
        }
    }

    @Nested
    @DisplayName("취소된 주문내역 리스트를 반환한다")
    class getCancelOrders {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(orderService.getCancelOrders(any()))
                .willReturn(ORDER_주문내역_조회_INFO());

            mockMvc.perform(get(ORDER_DEFAULT_URL + "/cancel")
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                )
                .andDo(print())
                .andDo(document("order/getCancelOrders",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                            headerWithName("Authorization")
                                .description("JWT Access Token")
                        ),
                        responseFields(ORDER_주문내역_조회_응답_필드())
                    )
                )
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("주문내역을 삭제한다")
    class delete {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            willDoNothing()
                .given(orderService)
                .delete(any(), any());

            mockMvc.perform(delete(ORDER_DEFAULT_URL + "/{id}", orderId)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("order/delete",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("주문 id")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 주문내역")
        void fail() throws Exception {
            doThrow(new OrderNotFoundException())
                .when(orderService)
                .delete(any(), any());

            mockMvc.perform(delete(ORDER_DEFAULT_URL + "/{id}", orderId)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("order/delete/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("존재하지 않는 주문 id")
                    )
                ))
                .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("실패: 다른유저의 주문내역")
        void fail2() throws Exception {
            doThrow(new DifferentUserException())
                .when(orderService)
                .delete(any(), any());

            mockMvc.perform(delete(ORDER_DEFAULT_URL + "/{id}", orderId)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("order/delete/fail/forbidden",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("다른 유저의 JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("주문 id")
                    )
                ))
                .andExpect(status().isForbidden());
        }
    }
}
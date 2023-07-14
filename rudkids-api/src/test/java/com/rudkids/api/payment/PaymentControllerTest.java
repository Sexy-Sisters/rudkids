package com.rudkids.api.payment;

import com.rudkids.api.common.ControllerTest;
import com.rudkids.core.order.exception.InvalidAmountException;
import com.rudkids.core.payment.exception.PaymentConfirmFailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.api.common.fixtures.payment.PaymentFixturesAndDocs.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PaymentControllerTest extends ControllerTest {

    @Nested
    @DisplayName("결제를 승인한다")
    class confirm {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            willDoNothing()
                .given(paymentService)
                .confirm(any());

            mockMvc.perform(post(PAYMENT_DEFAULT_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(PAYMENT_결제_승인_요청())))
                .andDo(print())
                .andDo(document("payment/confirm",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
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
        @DisplayName("실패: 잘못된 결제금액")
        void fail3() throws Exception {
            doThrow(new InvalidAmountException())
                .when(paymentService)
                .confirm(any());

            mockMvc.perform(post(PAYMENT_DEFAULT_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(PAYMENT_결제_승인_요청())))
                .andDo(print())
                .andDo(document("payment/confirm/fail/badRequest",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
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
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패: 외부 API 통신")
        void fail4() throws Exception {
            doThrow(new PaymentConfirmFailException())
                .when(paymentService)
                .confirm(any());

            mockMvc.perform(post(PAYMENT_DEFAULT_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(PAYMENT_결제_승인_요청())))
                .andDo(print())
                .andDo(document("payment/confirm/fail/internal",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
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
                .andExpect(status().isInternalServerError());
        }
    }

    @Nested
    @DisplayName("결제위젯 생성 정보를 반환한다")
    class getWidgetInformation {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(paymentService.getWidgetInformation(any()))
                .willReturn(PAYMENT_위젯_정보_응답());

            mockMvc.perform(get(PAYMENT_DEFAULT_URL)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("payment/getWidgetInformation",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    responseFields(
                        fieldWithPath("customerId")
                            .type(JsonFieldType.STRING)
                            .description("유저 id"),

                        fieldWithPath("amount")
                            .type(JsonFieldType.NUMBER)
                            .description("결제 총 가격")
                    )
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("결제요청 정보를 반환한다")
    class getInformation {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(paymentService.getInformation(any(), any()))
                .willReturn(PAYMENT_정보_응답());

            mockMvc.perform(get(PAYMENT_DEFAULT_URL + "/{id}", ORDER_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("payment/getInformation",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    pathParameters(
                        parameterWithName("id")
                            .description("주문 id")
                    ),
                    responseFields(
                        fieldWithPath("orderId")
                            .type(JsonFieldType.STRING)
                            .description("주문 id"),

                        fieldWithPath("orderName")
                            .type(JsonFieldType.STRING)
                            .description("결제 주문이름"),

                        fieldWithPath("customerName")
                            .type(JsonFieldType.STRING)
                            .description("이름"),

                        fieldWithPath("customerEmail")
                            .type(JsonFieldType.STRING)
                            .description("이메일")
                    )
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("결제를 취소한다")
    class cancel {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            willDoNothing()
                .given(paymentService)
                .confirm(any());

            mockMvc.perform(post(PAYMENT_DEFAULT_URL + "/{id}/cancel", ORDER_ID)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(PAYMENT_결제_취소_요청())))
                .andDo(print())
                .andDo(document("payment/cancel",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    pathParameters(
                        parameterWithName("id")
                            .description("주문 id")
                    ),
                    requestFields(
                        fieldWithPath("paymentKey")
                            .type(JsonFieldType.STRING)
                            .description("결제 key"),

                        fieldWithPath("cancelReason")
                            .type(JsonFieldType.STRING)
                            .description("취소하는 이유"),

                        fieldWithPath("bankCode")
                            .type(JsonFieldType.STRING)
                            .description("은행 코드"),

                        fieldWithPath("refundAccountNumber")
                            .type(JsonFieldType.STRING)
                            .description("환불할 계좌번호"),

                        fieldWithPath("refundAccountHolderName")
                            .type(JsonFieldType.STRING)
                            .description("환불할 계좌 예금주 이름")
                    )
                ))
                .andExpect(status().isOk());
        }
    }
}

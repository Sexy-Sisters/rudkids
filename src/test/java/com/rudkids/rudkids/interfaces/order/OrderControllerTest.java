package com.rudkids.rudkids.interfaces.order;

import com.rudkids.rudkids.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.UUID;

import static com.rudkids.rudkids.common.fixtures.order.OrderControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest extends ControllerTest {

    @DisplayName("[주문-주문요청]")
    @Test
    void 주문을_한다() throws Exception {
        given(orderService.create(any(), any()))
            .willReturn(UUID.randomUUID());

        mockMvc.perform(post(ORDER_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ORDER_주문_요청())))
            .andDo(print())
            .andDo(document("order/create",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                requestFields(
                    fieldWithPath("receiverName")
                        .type(JsonFieldType.STRING)
                        .description("수신자"),

                    fieldWithPath("receiverPhone")
                        .type(JsonFieldType.STRING)
                        .description("전화번호"),

                    fieldWithPath("receiverAddress1")
                        .type(JsonFieldType.STRING)
                        .description("주소1"),

                    fieldWithPath("receiverAddress2")
                        .type(JsonFieldType.STRING)
                        .description("주소2"),

                    fieldWithPath("receiverZipcode")
                        .type(JsonFieldType.STRING)
                        .description("우편번"),

                    fieldWithPath("etcMessage")
                        .type(JsonFieldType.STRING)
                        .description("배송시 요청사항"),

                    fieldWithPath("payMethod")
                        .type(JsonFieldType.STRING)
                        .description("결제수단")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[주문-배송 정보 수정]")
    @Test
    void 배송_정보를_수정한다() throws Exception {
        willDoNothing()
            .given(orderService)
            .updateDeliveryFragment(any(), any());

        mockMvc.perform(patch(ORDER_DEFAULT_URL + "/{id}", orderId)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ORDER_배송_정보_수정_요청())))
            .andDo(print())
            .andDo(document("order/updateDeliveryFragment",
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
                    fieldWithPath("receiverName")
                        .type(JsonFieldType.STRING)
                        .description("수신자"),

                    fieldWithPath("receiverPhone")
                        .type(JsonFieldType.STRING)
                        .description("전화번호"),

                    fieldWithPath("receiverAddress1")
                        .type(JsonFieldType.STRING)
                        .description("주소1"),

                    fieldWithPath("receiverAddress2")
                        .type(JsonFieldType.STRING)
                        .description("주소2"),

                    fieldWithPath("receiverZipcode")
                        .type(JsonFieldType.STRING)
                        .description("우편번"),

                    fieldWithPath("etcMessage")
                        .type(JsonFieldType.STRING)
                        .description("배송시 요청사항")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[주문-취소]")
    @Test
    void 주문을_취소한다() throws Exception {
        willDoNothing()
            .given(orderService)
            .delete(any());


        mockMvc.perform(delete(ORDER_DEFAULT_URL + "/{id}", orderId)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
            )
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
}
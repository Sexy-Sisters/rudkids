package com.rudkids.rudkids.interfaces.order;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.order.exception.OrderNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.order.OrderControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerFailTest extends ControllerTest {

    @DisplayName("[주문-배송 정보 수정-OrderNotFoundException]")
    @Test
    void 존재하지_않는_주문의_배송_정보를_수정한다() throws Exception {
        doThrow(new OrderNotFoundException())
            .when(orderService)
            .updateDeliveryFragment(any(), any());

        mockMvc.perform(patch(ORDER_DEFAULT_URL + "/{id}", orderId)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ORDER_배송_정보_수정_요청())))
            .andDo(print())
            .andDo(document("order/updateDeliveryFragment/failByNotFoundError",
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
                ),
                responseFields(Error_응답_필드())
            ))
            .andExpect(status().isNotFound());
    }

    @DisplayName("[주문-상세조회-OrderNotFoundException]")
    @Test
        void 존재하지_않는_주문을_상세조회_시_예외가_발생한다() throws Exception {
        doThrow(new OrderNotFoundException())
            .when(orderService)
            .find(any());


        mockMvc.perform(get(ORDER_DEFAULT_URL + "/{id}", orderId))
            .andDo(print())
            .andDo(document("order/find/failByNotFoundError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("id")
                        .description("존재하지 않는 주문 id")
                ),
                responseFields(Error_응답_필드())
            ))
            .andExpect(status().isNotFound());
    }

    @DisplayName("[주문-취소-OrderNotFoundException]")
    @Test
    void 존재하지_않는_주문을_취소_시_예외가_발생한다() throws Exception {
        doThrow(new OrderNotFoundException())
            .when(orderService)
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
                        .description("존재하지 않는 주문 id")
                ),
                responseFields(Error_응답_필드())
            ))
            .andExpect(status().isNotFound());
    }
}

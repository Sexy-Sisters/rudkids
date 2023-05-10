package com.rudkids.rudkids.interfaces.order;

import com.rudkids.rudkids.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

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
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
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
                requestFields(ORDER_주문_요청_필드())
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[주문-상세 조회]")
    @Test
    void 주문_세부사항을_조회한다() throws Exception {
        given(orderService.find(any()))
            .willReturn(ORDER_상세조회_응답());

        mockMvc.perform(get(ORDER_DEFAULT_URL + "/{id}", orderId))
            .andDo(print())
            .andDo(document("order/find",
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

    @DisplayName("[주문-주문 내역 조회]")
    @Test
    void 자신의_주문_내역을_조회한다() throws Exception {
        given(orderService.findAllMine(any()))
            .willReturn(ORDER_주문내역_조회_INFO());

        given(orderDtoMapper.toResponse(any()))
            .willReturn(ORDER_주문내역_조회_응답());

        mockMvc.perform(get(ORDER_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
            )
            .andDo(print())
            .andDo(document("order/findAllMine",
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
                requestFields(ORDER_배송정보_수정_요청_필드())
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
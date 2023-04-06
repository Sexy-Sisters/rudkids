package com.rudkids.rudkids.interfaces.cart;

import com.rudkids.rudkids.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.cart.CartControllerFixtures.*;
import static com.rudkids.rudkids.common.fixtures.user.UserControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CartControllerTest extends ControllerTest {

    @DisplayName("장바구니에 아이템을 추가한다.")
    @Test
    void 장바구니에_아이템을_추가한다() throws Exception {
        willDoNothing()
                .given(cartService)
                .addCartItem(any(), any());

        mockMvc.perform(post(CART_DEFAULT_URL)
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CART_아이템_추가_요청())))
                .andDo(print())
                .andDo(document("cart/addCartItem",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("JWT Access Token")
                        ),
                        requestFields(
                                fieldWithPath("itemId")
                                        .type(JsonFieldType.STRING)
                                        .description("아이템 ID"),

                                fieldWithPath("amount")
                                        .type(JsonFieldType.NUMBER)
                                        .description("아이템 수량")
                        )
                ))
                .andExpect(status().isOk());
    }

    @DisplayName("장바구니에 담겨있는 아이템 리스트를 조회한다.")
    @Test
    void 장바구니에_담겨있는_아이템_리스트를_조회한다() throws Exception {
        given(cartService.findCartItems(any()))
                .willReturn(CART_아이템_리스트());

        mockMvc.perform(get(CART_DEFAULT_URL)
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("cart/findCartItems",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("JWT Access Token")
                        ),
                        responseFields(
                                fieldWithPath("totalCartItemPrice")
                                        .type(JsonFieldType.NUMBER)
                                        .description("장바구니아이템 총 가격"),

                                fieldWithPath("cartItems.[].id")
                                        .type(JsonFieldType.STRING)
                                        .description("장바구니아이템 id"),

                                fieldWithPath("cartItems.[].name")
                                        .type(JsonFieldType.STRING)
                                        .description("장바구니아이템 이름"),

                                fieldWithPath("cartItems.[].price")
                                        .type(JsonFieldType.NUMBER)
                                        .description("장바구니아이템 가격"),

                                fieldWithPath("cartItems.[].itemStatus")
                                        .type(JsonFieldType.STRING)
                                        .description("장바구니아이템 상태")
                        )
                ))
                .andExpect(status().isOk());
    }

    @DisplayName("장바구니 아이템 수량을 변경한다.")
    @Test
    void 장바구니에_아이템_수량을_변경한다() throws Exception {
        willDoNothing()
                .given(cartService)
                .updateCartItemAmount(any(), any());

        mockMvc.perform(post(CART_DEFAULT_URL)
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CART_아이템_수량_변경_요청())))
                .andDo(print())
                .andDo(document("cart/updateCartItemAmount",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("JWT Access Token")
                        ),
                        requestFields(
                                fieldWithPath("cartItemId")
                                        .type(JsonFieldType.STRING)
                                        .description("장바구니아이템 ID"),

                                fieldWithPath("amount")
                                        .type(JsonFieldType.NUMBER)
                                        .description("장바구니아이템 수량")
                        )
                ))
                .andExpect(status().isOk());
    }
}
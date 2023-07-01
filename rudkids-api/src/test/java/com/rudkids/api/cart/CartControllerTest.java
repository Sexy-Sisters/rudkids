package com.rudkids.api.cart;

import com.rudkids.api.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.api.common.fixtures.CartControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CartControllerTest extends ControllerTest {

    @DisplayName("[장바구니-아이템추가]")
    @Test
    void 장바구니에_아이템을_추가한다() throws Exception {
        given(cartService.addCartItem(any(), any()))
            .willReturn(CART_아이템_ID);

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
                    fieldWithPath("itemName")
                        .type(JsonFieldType.STRING)
                        .description("아이템 이름"),

                    fieldWithPath("optionGroups")
                        .type(JsonFieldType.ARRAY)
                        .description("아이템 옵션 그룹"),

                    fieldWithPath("optionGroups.[].name")
                        .type(JsonFieldType.STRING)
                        .description("아이템 옵션 그룹 이름"),

                    fieldWithPath("optionGroups.[].option")
                        .type(JsonFieldType.OBJECT)
                        .description("아이템 옵션"),

                    fieldWithPath("optionGroups.[].option.name")
                        .type(JsonFieldType.STRING)
                        .description("아이템 옵션 이름"),

                    fieldWithPath("optionGroups.[].option.price")
                        .type(JsonFieldType.NUMBER)
                        .description("아이템 옵션 가격"),

                    fieldWithPath("amount")
                        .type(JsonFieldType.NUMBER)
                        .description("아이템 수량")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[장바구니-아이템전체조회]")
    @Test
    void 장바구니에_담겨있는_아이템_리스트를_조회한다() throws Exception {
        given(cartService.getCartItems(any()))
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

                    fieldWithPath("cartItems.[].imageUrl")
                        .type(JsonFieldType.STRING)
                        .description("장바구니아이템 이미지 url"),

                    fieldWithPath("cartItems.[].name")
                        .type(JsonFieldType.STRING)
                        .description("장바구니아이템 이름"),

                    fieldWithPath("cartItems.[].price")
                        .type(JsonFieldType.NUMBER)
                        .description("장바구니아이템 가격"),

                    fieldWithPath("cartItems.[].amount")
                        .type(JsonFieldType.NUMBER)
                        .description("장바구니아이템 수량"),

                    fieldWithPath("cartItems.[].optionGroups")
                        .type(JsonFieldType.ARRAY)
                        .description("장바구니아이템 옵션 그룹"),

                    fieldWithPath("cartItems.[].optionGroups.[].name")
                        .type(JsonFieldType.STRING)
                        .description("장바구니아이템 옵션 그룹 이름"),

                    fieldWithPath("cartItems.[].optionGroups.[].optionName")
                        .type(JsonFieldType.STRING)
                        .description("장바구니아이템 옵션 이름"),

                    fieldWithPath("cartItems.[].itemStatus")
                        .type(JsonFieldType.STRING)
                        .description("장바구니아이템 상태")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[장바구니-아이템수량변경]")
    @Test
    void 장바구니에_아이템_수량을_변경한다() throws Exception {
        willDoNothing()
            .given(cartService)
            .updateCartItemAmount(any(), any());

        mockMvc.perform(patch(CART_DEFAULT_URL)
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

    @DisplayName("[장바구니-아이템선택삭제]")
    @Test
    void 장바구니_아이템들을_선택하여_삭제한다() throws Exception {
        willDoNothing()
            .given(cartService)
            .deleteCartItems(any(), any());

        mockMvc.perform(delete(CART_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CART_아이템_선택삭제_변경_요청())))
            .andDo(print())
            .andDo(document("cart/deleteCartItems",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                requestFields(
                    fieldWithPath("cartItemIds")
                        .type(JsonFieldType.ARRAY)
                        .description("장바구니 아이템 ID 리스트")
                )
            ))
            .andExpect(status().isOk());
    }
}
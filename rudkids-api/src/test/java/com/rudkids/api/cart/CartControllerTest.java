package com.rudkids.api.cart;

import com.rudkids.api.common.ControllerTest;
import com.rudkids.core.cart.exception.CartItemNotFoundException;
import com.rudkids.core.item.exception.ItemNotFoundException;
import com.rudkids.core.user.exception.DifferentUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.api.common.fixtures.cart.CartFixturesAndDocs.*;
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
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CartControllerTest extends ControllerTest {

    @Nested
    @DisplayName("장바구니에 아이템을 추가한다")
    class addCartItem {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
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

        @Test
        @DisplayName("실패: 존재하지 않는 아이템")
        void fail() throws Exception {
            doThrow(new ItemNotFoundException())
                .when(cartService)
                .addCartItem(any(), any());

            mockMvc.perform(post(CART_DEFAULT_URL)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(CART_아이템_추가_요청())))
                .andDo(print())
                .andDo(document("cart/addCartItem/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    requestFields(
                        fieldWithPath("itemName")
                            .type(JsonFieldType.STRING)
                            .description("존재하지 않는 아이템 이름"),

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
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("장바구니에 담겨있는 아이템들을 조회한다")
    class getCartItems {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(cartService.getCartItems(any()))
                .willReturn(CART_아이템_리스트);

            mockMvc.perform(get(CART_DEFAULT_URL)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("cart/getCartItems",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    responseFields(
                        fieldWithPath("[]id")
                            .type(JsonFieldType.STRING)
                            .description("장바구니아이템 id"),

                        fieldWithPath("[]imageUrl")
                            .type(JsonFieldType.STRING)
                            .description("장바구니아이템 이미지 url"),

                        fieldWithPath("[]name")
                            .type(JsonFieldType.STRING)
                            .description("장바구니아이템 이름"),

                        fieldWithPath("[]price")
                            .type(JsonFieldType.NUMBER)
                            .description("장바구니아이템 가격"),

                        fieldWithPath("[]amount")
                            .type(JsonFieldType.NUMBER)
                            .description("장바구니아이템 수량"),

                        fieldWithPath("[]itemStatus")
                            .type(JsonFieldType.STRING)
                            .description("장바구니아이템 상태")
                    )
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("장바구니에 담겨있는 아이템들 중 선택된 아이템들만 조회한다")
    class getSelected {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(cartService.getSelected(any()))
                .willReturn(CART_선택된_아이템_응답());

            mockMvc.perform(get(CART_DEFAULT_URL + "/select")
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("cart/getSelected",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    responseFields(
                        fieldWithPath("totalPrice")
                            .type(JsonFieldType.NUMBER)
                            .description("장바구니아이템 총 가격"),

                        fieldWithPath("orderName")
                            .type(JsonFieldType.STRING)
                            .description("결제주문 이름"),

                        fieldWithPath("selectedCartItems[]name")
                            .type(JsonFieldType.STRING)
                            .description("장바구니아이템 이름"),

                        fieldWithPath("selectedCartItems[]amount")
                            .type(JsonFieldType.NUMBER)
                            .description("장바구니아이템 수량")
                    )
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("장바구니아이템의 수량을 변경한다")
    class updateCartItemAmount {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
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

        @Test
        @DisplayName("실패: 다른유저의 장바구니아이템")
        void fail() throws Exception {
            doThrow(new DifferentUserException())
                .when(cartService)
                .updateCartItemAmount(any(), any());

            mockMvc.perform(patch(CART_DEFAULT_URL)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(CART_아이템_수량_변경_요청())))
                .andDo(print())
                .andDo(document("cart/updateCartItemAmount/fail/forbidden",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("다른 사용자의 JWT Access Token")
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
                .andExpect(status().isForbidden());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 장바구니아이템")
        void fail2() throws Exception {
            doThrow(new CartItemNotFoundException())
                .when(cartService)
                .updateCartItemAmount(any(), any());

            mockMvc.perform(patch(CART_DEFAULT_URL)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(CART_아이템_수량_변경_요청())))
                .andDo(print())
                .andDo(document("cart/updateCartItemAmount/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("다른 사용자의 JWT Access Token")
                    ),
                    requestFields(
                        fieldWithPath("cartItemId")
                            .type(JsonFieldType.STRING)
                            .description("존재하지 않는 장바구니아이템 ID"),

                        fieldWithPath("amount")
                            .type(JsonFieldType.NUMBER)
                            .description("장바구니아이템 수량")
                    )
                ))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("장바구니 아이템을 삭제한다")
    class deleteCartItem {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            willDoNothing()
                .given(cartService)
                .deleteCartItem(any(), any());

            mockMvc.perform(delete(CART_DEFAULT_URL + "/{id}", CART_아이템_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("cart/deleteCartItem",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("장바구니 아이템 id")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 다른유저의 장바구니아이템")
        void fail() throws Exception {
            doThrow(new DifferentUserException())
                .when(cartService)
                .deleteCartItem(any(), any());

            mockMvc.perform(delete(CART_DEFAULT_URL + "/{id}", CART_아이템_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("cart/deleteCartItem/fail/forbidden",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("다른 사용자의 JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("장바구니 아이템 id")
                    )
                ))
                .andExpect(status().isForbidden());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 장바구니아이템")
        void fail2() throws Exception {
            doThrow(new CartItemNotFoundException())
                .when(cartService)
                .deleteCartItem(any(), any());

            mockMvc.perform(delete(CART_DEFAULT_URL + "/{id}", CART_아이템_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("cart/deleteCartItem/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("장바구니 아이템 id")
                    )
                ))
                .andExpect(status().isNotFound());
        }
    }
}
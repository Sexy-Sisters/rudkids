package com.rudkids.rudkids.interfaces.cart;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.cart.exception.CartItemNotFoundException;
import com.rudkids.rudkids.domain.item.exception.ItemNotFoundException;
import com.rudkids.rudkids.domain.user.exception.DifferentUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.cart.CartControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CartControllerFailTest extends ControllerTest {

    @DisplayName("[장바구니-아이템추가-404-에러]")
    @Test
    void 장바구니에_존재하지_않는아이템을_추가할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new ItemNotFoundException())
                .when(cartService)
                .addCartItem(any(), any());

        mockMvc.perform(post(CART_DEFAULT_URL)
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CART_아이템_추가_요청())))
                .andDo(print())
                .andDo(document("cart/addCartItem/failByNotFoundError",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("JWT Access Token")
                        ),
                        requestFields(
                                fieldWithPath("itemId")
                                        .type(JsonFieldType.STRING)
                                        .description("존재하지 않는 아이템 ID"),

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

    @DisplayName("[장바구니-아이템수량변경-403-에러]")
    @Test
    void 다른_사용자의_장바구니에_아이템_수량을_변경할_시_상태코드_403을_반환한다() throws Exception {
        doThrow(new DifferentUserException())
                .when(cartService)
                .updateCartItemAmount(any(), any());

        mockMvc.perform(patch(CART_DEFAULT_URL)
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CART_아이템_수량_변경_요청())))
                .andDo(print())
                .andDo(document("cart/updateCartItemAmount/failByDifferentUserError",
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

    @DisplayName("[장바구니-아이템수량변경-404-에러]")
    @Test
    void 존재하지_않는_장바구니에_아이템_수량을_변경할_시_상태코드_404을_반환한다() throws Exception {
        doThrow(new CartItemNotFoundException())
                .when(cartService)
                .updateCartItemAmount(any(), any());

        mockMvc.perform(patch(CART_DEFAULT_URL)
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CART_아이템_수량_변경_요청())))
                .andDo(print())
                .andDo(document("cart/updateCartItemAmount/failByNotFoundError",
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

    @DisplayName("[장바구니-아이템선택삭제-403-에러]")
    @Test
    void 다른_사용자의_장바구니_아이템을_선택하여_삭제할_시_상태코드_403을_반환한다() throws Exception {
        doThrow(new DifferentUserException())
                .when(cartService)
                .deleteCartItems(any(), any());

        mockMvc.perform(delete(CART_DEFAULT_URL)
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CART_아이템_선택삭제_변경_요청())))
                .andDo(print())
                .andDo(document("cart/deleteCartItems/failByDifferentUserError",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("다른 사용자의 JWT Access Token")
                        ),
                        requestFields(
                                fieldWithPath("cartItemIds")
                                        .type(JsonFieldType.ARRAY)
                                        .description("장바구니 아이템 ID 리스트")
                        )
                ))
                .andExpect(status().isForbidden());
    }

    @DisplayName("[장바구니-아이템선택삭제-404-에러]")
    @Test
    void 존재하지_않는_장바구니_아이템을_선택하여_삭제할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new CartItemNotFoundException())
            .when(cartService)
            .deleteCartItems(any(), any());

        mockMvc.perform(delete(CART_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CART_아이템_선택삭제_변경_요청())))
            .andDo(print())
            .andDo(document("cart/deleteCartItems/failByNotFoundError",
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
            .andExpect(status().isNotFound());
    }
}

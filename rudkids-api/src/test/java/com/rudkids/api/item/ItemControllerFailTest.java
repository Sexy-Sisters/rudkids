package com.rudkids.api.item;

import com.rudkids.api.common.ControllerTest;
import com.rudkids.core.item.exception.ItemNotFoundException;
import com.rudkids.core.product.exception.ProductNotFoundException;
import com.rudkids.core.user.exception.NotAdminOrPartnerRoleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.api.common.fixtures.ItemControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
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

public class ItemControllerFailTest extends ControllerTest {

    @DisplayName("[아이템-생성-403-에러")
    @Test
    void 관리자와_파트너_권한_이외의_유저가_아이템을_등록하면_상태코드_403을_반환한다() throws Exception {
        doThrow(new NotAdminOrPartnerRoleException())
            .when(itemService)
            .create(any(), any(), any());

        mockMvc.perform(post(ITEM_DEFAULT_URL + "/{productId}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ITEM_등록_요청()))
            ).andDo(print())
            .andDo(document("item/create/failByForbiddenError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("productId")
                        .description("프로덕트 id")
                ),
                requestFields(
                    fieldWithPath("enName")
                        .type(JsonFieldType.STRING)
                        .description("상품 영어 이름"),

                    fieldWithPath("koName")
                        .type(JsonFieldType.STRING)
                        .description("상품 한국 이름"),

                    fieldWithPath("itemBio")
                        .type(JsonFieldType.STRING)
                        .description("소개글"),

                    fieldWithPath("price")
                        .type(JsonFieldType.NUMBER)
                        .description("가격"),

                    fieldWithPath("quantity")
                        .type(JsonFieldType.NUMBER)
                        .description("수량"),

                    fieldWithPath("limitType")
                        .type(JsonFieldType.STRING)
                        .description("수량 한정 여부"),

                    fieldWithPath("images")
                        .type(JsonFieldType.ARRAY)
                        .description("여러 아이템 이미지"),

                    fieldWithPath("images[].path")
                        .type(JsonFieldType.STRING)
                        .description("아이템 이미지 주소"),

                    fieldWithPath("images[].url")
                        .type(JsonFieldType.STRING)
                        .description("아이템 이미지 url"),

                    fieldWithPath("itemOptionGroupList[].itemOptionGroupName")
                        .type(JsonFieldType.STRING)
                        .description("옵션 그룹 이름"),

                    fieldWithPath("itemOptionGroupList[].itemOptionList[].itemOptionName")
                        .type(JsonFieldType.STRING)
                        .description("옵션 이름"),

                    fieldWithPath("itemOptionGroupList[].itemOptionList[].itemOptionPrice")
                        .type(JsonFieldType.NUMBER)
                        .description("옵션 가격"),

                    fieldWithPath("videoImage")
                        .type(JsonFieldType.OBJECT)
                        .description("콘티영상 url"),

                    fieldWithPath("videoImage.path")
                        .type(JsonFieldType.STRING)
                        .description("콘티영상 이미지 path"),

                    fieldWithPath("videoImage.url")
                        .type(JsonFieldType.STRING)
                        .description("콘티영상 이미지 url"),

                    fieldWithPath("videoUrl")
                        .type(JsonFieldType.STRING)
                        .description("콘티영상 url")
                ),
                responseFields(
                    fieldWithPath("message")
                        .type(JsonFieldType.STRING)
                        .description("에러 메시지")
                )
            ))
            .andExpect(status().isForbidden());
    }

    @DisplayName("[아이템-생성-404-에러]")
    @Test
    void 존재하지_않는_프로덕트에_상품을_등록하면_상태코드_404를_반환한다() throws Exception {
        doThrow(new ProductNotFoundException())
            .when(itemService)
            .create(any(), any(), any());

        mockMvc.perform(post(ITEM_DEFAULT_URL + "/{productId}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ITEM_등록_요청()))
            ).andDo(print())
            .andDo(document("item/create/failByNotFoundError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("productId")
                        .description("존재하지 않는 프로덕트 id")
                ),
                requestFields(
                    fieldWithPath("enName")
                        .type(JsonFieldType.STRING)
                        .description("상품 영어 이름"),

                    fieldWithPath("koName")
                        .type(JsonFieldType.STRING)
                        .description("상품 한국 이름"),

                    fieldWithPath("itemBio")
                        .type(JsonFieldType.STRING)
                        .description("소개글"),

                    fieldWithPath("price")
                        .type(JsonFieldType.NUMBER)
                        .description("가격"),

                    fieldWithPath("quantity")
                        .type(JsonFieldType.NUMBER)
                        .description("수량"),

                    fieldWithPath("limitType")
                        .type(JsonFieldType.STRING)
                        .description("수량 한정 여부"),

                    fieldWithPath("images")
                        .type(JsonFieldType.ARRAY)
                        .description("여러 아이템 이미지"),

                    fieldWithPath("images[].path")
                        .type(JsonFieldType.STRING)
                        .description("아이템 이미지 주소"),

                    fieldWithPath("images[].url")
                        .type(JsonFieldType.STRING)
                        .description("아이템 이미지 url"),

                    fieldWithPath("itemOptionGroupList[].itemOptionGroupName")
                        .type(JsonFieldType.STRING)
                        .description("옵션 그룹 이름"),

                    fieldWithPath("itemOptionGroupList[].itemOptionList[].itemOptionName")
                        .type(JsonFieldType.STRING)
                        .description("옵션 이름"),

                    fieldWithPath("itemOptionGroupList[].itemOptionList[].itemOptionPrice")
                        .type(JsonFieldType.NUMBER)
                        .description("옵션 가격"),

                    fieldWithPath("videoImage")
                        .type(JsonFieldType.OBJECT)
                        .description("콘티영상 url"),

                    fieldWithPath("videoImage.path")
                        .type(JsonFieldType.STRING)
                        .description("콘티영상 이미지 path"),

                    fieldWithPath("videoImage.url")
                        .type(JsonFieldType.STRING)
                        .description("콘티영상 이미지 url"),

                    fieldWithPath("videoUrl")
                        .type(JsonFieldType.STRING)
                        .description("콘티영상 url")
                ),
                responseFields(
                    fieldWithPath("message")
                        .type(JsonFieldType.STRING)
                        .description("에러 메시지")
                )
            ))
            .andExpect(status().isNotFound());
    }

    @DisplayName("[아이템-상세조회-404-에러]")
    @Test
    void 존재하지_않는_아이템_상세정보를_조회할_때_상태코드_404를_반환한다() throws Exception {
        doThrow(new ItemNotFoundException())
            .when(itemService)
            .get(any());

        mockMvc.perform(get(ITEM_DEFAULT_URL + "?name={name}", 아이템_영어_이름))
            .andDo(print())
            .andDo(document("item/find/failByNotFoundError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                queryParameters(
                    parameterWithName("name")
                        .description("아이템 영어이름")
                ),
                responseFields(
                    fieldWithPath("message")
                        .type(JsonFieldType.STRING)
                        .description("에러 메시지")
                )
            ))
            .andExpect(status().isNotFound());
    }

    @DisplayName("[아이템-상태변경-403-에러")
    @Test
    void 관리자와_파트너_권한_이외의_유저가_아이템의_상태를_변경하면_상태코드_403을_반환한다() throws Exception {
        doThrow(new NotAdminOrPartnerRoleException())
            .when(itemService)
            .changeStatus(any(), any(), any());

        mockMvc.perform(put(ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ITEM_상태_변경_요청()))
            )
            .andDo(print())
            .andDo(document("item/changeStatus/failByForbiddenError",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("아이템 id")
                    ),
                    responseFields(
                        fieldWithPath("message")
                            .type(JsonFieldType.STRING)
                            .description("에러 메세지")
                    )
                )
            )
            .andExpect(status().isForbidden());
    }

    @DisplayName("[아이템-상태변경-404-에러]")
    @Test
    void 존재하지_않는_아이템의_상태를_변경_할_때_상태코드_404를_반환한다() throws Exception {
        doThrow(new ItemNotFoundException())
            .when(itemService)
            .changeStatus(any(), any(), any());

        mockMvc.perform(put(ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ITEM_상태_변경_요청()))
            )
            .andDo(print())
            .andDo(document("item/changeStatus/failByNotFoundError",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("존재하지 않는 아이템 id")
                    ),
                    responseFields(
                        fieldWithPath("message")
                            .type(JsonFieldType.STRING)
                            .description("에러 메세지")
                    )
                )
            )
            .andExpect(status().isNotFound());
    }

    @DisplayName("[아이템-수정-404-에러]")
    @Test
    void 존재하지_않는_아이템을_수정할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new ItemNotFoundException())
            .when(itemService)
            .update(any(), any(), any());

        mockMvc.perform(delete(ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ITEM_수정_요청()))
            )
            .andDo(print())
            .andDo(document("item/update",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("존재하지 않는 아이템 ID")
                ),
                requestFields(
                    fieldWithPath("enName")
                        .type(JsonFieldType.STRING)
                        .description("상품 영어 이름"),

                    fieldWithPath("koName")
                        .type(JsonFieldType.STRING)
                        .description("상품 한국 이름"),

                    fieldWithPath("itemBio")
                        .type(JsonFieldType.STRING)
                        .description("소개글"),

                    fieldWithPath("price")
                        .type(JsonFieldType.NUMBER)
                        .description("가격"),

                    fieldWithPath("quantity")
                        .type(JsonFieldType.NUMBER)
                        .description("수량"),

                    fieldWithPath("limitType")
                        .type(JsonFieldType.STRING)
                        .description("수량 한정 여부"),

                    fieldWithPath("images")
                        .type(JsonFieldType.ARRAY)
                        .description("여러 아이템 이미지"),

                    fieldWithPath("images[].path")
                        .type(JsonFieldType.STRING)
                        .description("아이템 이미지 주소"),

                    fieldWithPath("images[].url")
                        .type(JsonFieldType.STRING)
                        .description("아이템 이미지 url")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[아이템-삭제-404-에러]")
    @Test
    void 존재하지_않는_아이템을_삭제할_경우_상태코드_404를_반환한다() throws Exception {
        willDoNothing()
            .given(itemService)
            .delete(any(), any());

        mockMvc.perform(delete(ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
            .andDo(print())
            .andDo(document("item/delete",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("존재하지 않는 아이템 ID")
                )
            ))
            .andExpect(status().isOk());
    }
}

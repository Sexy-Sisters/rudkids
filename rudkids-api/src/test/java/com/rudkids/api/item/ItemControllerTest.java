package com.rudkids.api.item;

import com.rudkids.api.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.api.common.fixtures.ItemControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ItemControllerTest extends ControllerTest {

    @DisplayName("[아이템-생성]")
    @Test
    void 아이템을_등록한다() throws Exception {
        given(itemService.create(any(), any(), any()))
            .willReturn(아이템_아이디);

        mockMvc.perform(post(ITEM_DEFAULT_URL + "/{productId}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ITEM_등록_요청()))
            ).andDo(print())
            .andDo(document("item/create",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
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

                    fieldWithPath("itemOptionGroupList[].ordering")
                        .type(JsonFieldType.NUMBER)
                        .description("옵션 그룹 순서"),

                    fieldWithPath("itemOptionGroupList[].itemOptionGroupName")
                        .type(JsonFieldType.STRING)
                        .description("옵션 그룹 이름"),

                    fieldWithPath("itemOptionGroupList[].itemOptionList[].ordering")
                        .type(JsonFieldType.NUMBER)
                        .description("옵션 순서"),

                    fieldWithPath("itemOptionGroupList[].itemOptionList[].itemOptionName")
                        .type(JsonFieldType.STRING)
                        .description("옵션 이름"),

                    fieldWithPath("itemOptionGroupList[].itemOptionList[].itemOptionPrice")
                        .type(JsonFieldType.NUMBER)
                        .description("옵션 가격")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[아이템-상세조회]")
    @Test
    void 아이템_상세정보를_조회한다() throws Exception {
        given(itemService.get(any()))
            .willReturn(ITEM_상세정보_조회_응답());

        mockMvc.perform(get(ITEM_DEFAULT_URL + "?name={name}", 아이템_영어_이름))
            .andDo(print())
            .andDo(document("item/find",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                queryParameters(
                    parameterWithName("name")
                        .description("아이템 영어이름")
                ),
                responseFields(
                    fieldWithPath("enName")
                        .type(JsonFieldType.STRING)
                        .description("상품 영어 이름"),

                    fieldWithPath("koName")
                        .type(JsonFieldType.STRING)
                        .description("상품 한국 이름"),

                    fieldWithPath("price")
                        .type(JsonFieldType.NUMBER)
                        .description("가격"),

                    fieldWithPath("itemBio")
                        .type(JsonFieldType.STRING)
                        .description("소개글"),

                    fieldWithPath("quantity")
                        .type(JsonFieldType.NUMBER)
                        .description("수량"),

                    fieldWithPath("limitType")
                        .type(JsonFieldType.STRING)
                        .description("수량 한정 여부"),

                    fieldWithPath("imageUrls")
                        .type(JsonFieldType.ARRAY)
                        .description("여러 이미지 url"),

                    fieldWithPath("itemStatus")
                        .type(JsonFieldType.STRING)
                        .description("상태"),

                    fieldWithPath("itemOptionGroupInfoList")
                        .type(JsonFieldType.NULL)
                        .description("옵션 그룹 응답 리스트 (테스트 코드 수정 필요")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[아이템-상태변경]")
    @Test
    void 아이템_상태를_변경한다() throws Exception {
        given(itemService.changeStatus(any(), any(), any()))
            .willReturn(아이템_상태);

        mockMvc.perform(put(ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ITEM_상태_변경_요청()))
            )
            .andDo(print())
            .andDo(document("item/changeStatus",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("아이템 ID")
                ),
                requestFields(
                    fieldWithPath("itemStatus")
                        .type(JsonFieldType.STRING)
                        .description("아이템 상태")
                )
            ))
            .andExpect(status().isOk());

    }

    @DisplayName("[아이템-수정]")
    @Test
    void 아이템을_수정한다() throws Exception {
        willDoNothing()
            .given(itemService)
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
                    parameterWithName("id").description("아이템 ID")
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

    @DisplayName("[아이템-삭제]")
    @Test
    void 아이템을_삭제한다() throws Exception {
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
                    parameterWithName("id").description("아이템 ID")
                )
            ))
            .andExpect(status().isOk());

    }
}
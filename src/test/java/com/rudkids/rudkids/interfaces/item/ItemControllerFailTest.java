package com.rudkids.rudkids.interfaces.item;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.item.exception.ItemNotFoundException;
import com.rudkids.rudkids.domain.product.exception.ProductNotFoundException;
import com.rudkids.rudkids.domain.user.exception.NotAdminOrPartnerRoleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.item.ItemControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ItemControllerFailTest extends ControllerTest {

    @DisplayName("관리자와 파트너 권한 이외의 유저가 아이템을 등록하면 403을 반환한다.")
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
                requestFields(
                    fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .description("상품명"),

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
                ),
                responseFields(
                    fieldWithPath("message")
                        .type(JsonFieldType.STRING)
                        .description("에러 메시지")
                )
            ))
            .andExpect(status().isForbidden());
    }

    @DisplayName("존재하지 않는 프로덕트에 상품을 등록하면 404를 반환한다.")
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
                requestFields(
                    fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .description("상품명"),

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
                ),
                responseFields(
                    fieldWithPath("message")
                        .type(JsonFieldType.STRING)
                        .description("에러 메시지")
                )
            ))
            .andExpect(status().isNotFound());
    }

    @DisplayName("존재하지 않는 아이템 상세정보를 조회할 때 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_아이템_상세정보를_조회할_때_상태코드_404를_반환한다() throws Exception {
        doThrow(new ItemNotFoundException())
            .when(itemService)
            .find(any());

        mockMvc.perform(get(ITEM_DEFAULT_URL + "/detail/{id}", 아이템_아이디))
            .andDo(print())
            .andDo(document("item/find/failByNotFoundError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("id")
                        .description("존재하지 않는 아이ㅇ id")
                ),
                responseFields(
                    fieldWithPath("message")
                        .type(JsonFieldType.STRING)
                        .description("에러 메시지")
                )
            ))
            .andExpect(status().isNotFound());
    }

    @DisplayName("존재하지 않는 아이템의 상태를 변경 할 때 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_아이템의_상태를_변경_할_때_상태코드_404를_반환한다() throws Exception {
        doThrow(new ItemNotFoundException())
            .when(itemService)
            .changeItemStatus(any(), any());

        mockMvc.perform(
                put(ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                    .param("status", "SELLING")
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
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
                            .description("아이템 id")
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
}

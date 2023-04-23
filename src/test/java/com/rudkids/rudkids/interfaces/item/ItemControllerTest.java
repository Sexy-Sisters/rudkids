package com.rudkids.rudkids.interfaces.item;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.item.ItemInfo;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.item.ItemControllerFixtures.*;
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
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ItemControllerTest extends ControllerTest {

    @DisplayName("아이템을 등록한다.")
    @Test
    void 아이템을_등록한다() throws Exception {
        willDoNothing()
            .given(itemService)
            .create(any(), any(), any());

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
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("아이템 상세정보를 조회한다.")
    @Test
    void 아이템_상세정보를_조회한다() throws Exception {
        given(itemService.find(any()))
            .willReturn(ITEM_상세정보_조회_응답());

        given(itemDtoMapper.to((ItemInfo.Detail) any()))
            .willReturn(ITEM_상세정보_조회_DTO_응답());

        mockMvc.perform(get(ITEM_DEFAULT_URL + "/detail/{id}", 아이템_아이디))
            .andDo(print())
            .andDo(document("item/find",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("id")
                        .description("아이템 id")
                ),
                responseFields(
                    fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .description("이름"),

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

                    fieldWithPath("itemStatus")
                        .type(JsonFieldType.STRING)
                        .description("상태"),

                    fieldWithPath("itemOptionGroupResponseList")
                        .type(JsonFieldType.NULL)
                        .description("옵션 그룹 응답 리스트 (테스트 코드 수정 필요")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("아이템을 판매중 상태로 변경한다.")
    @Test
    void 아이템을_판매중_상태로_변경한다() throws Exception {
        given(itemService.changeOnSales(any()))
            .willReturn(ItemStatus.SELLING);

        mockMvc.perform(put(ITEM_DEFAULT_URL + "/{id}/on-sales", 아이템_아이디))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("아이템을_판매종료한다.")
    @Test
    void 아이템을_판매종료한다() throws Exception {
        given(itemService.changeEndOfSales(any()))
            .willReturn(ItemStatus.SOLD_OUT);

        mockMvc.perform(delete(ITEM_DEFAULT_URL + "/{id}/end-of-sales", 아이템_아이디))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("아이템을 준비중 상태로 변경한다.")
    @Test
    void 아이템을_준비중_상태로_변경한다() throws Exception {
        given(itemService.changePrepare(any()))
            .willReturn(ItemStatus.PREPARE);

        mockMvc.perform(put(ITEM_DEFAULT_URL + "/{id}/prepare", 아이템_아이디))
            .andDo(print())
            .andExpect(status().isOk());
    }
}
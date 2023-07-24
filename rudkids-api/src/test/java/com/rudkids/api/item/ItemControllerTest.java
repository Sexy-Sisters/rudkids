package com.rudkids.api.item;

import com.rudkids.api.common.ControllerTest;
import com.rudkids.core.item.exception.ItemNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.api.common.fixtures.item.ItemFixturesAndDocs.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ItemControllerTest extends ControllerTest {

    @Nested
    @DisplayName("아이템 이름으로 상세조회한다")
    class get {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(itemService.get(any()))
                .willReturn(ITEM_상세정보_조회_응답());

            mockMvc.perform(get(ITEM_DEFAULT_URL + "?name={name}", 아이템_영어_이름))
                .andDo(print())
                .andDo(document("item/get",
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

                        fieldWithPath("images")
                            .type(JsonFieldType.ARRAY)
                            .description("여러 이미지들"),

                        fieldWithPath("images[]path")
                            .type(JsonFieldType.STRING)
                            .description("여러 이미지 path"),

                        fieldWithPath("images[]url")
                            .type(JsonFieldType.STRING)
                            .description("여러 이미지 url"),

                        fieldWithPath("images[]ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("여러 이미지 순서"),

                        fieldWithPath("itemStatus")
                            .type(JsonFieldType.STRING)
                            .description("상태"),

                        fieldWithPath("itemOptionGroupInfoList")
                            .type(JsonFieldType.ARRAY)
                            .description("옵션 그룹 리스트"),

                        fieldWithPath("itemOptionGroupInfoList[]itemOptionGroupName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 그룹 이름"),

                        fieldWithPath("itemOptionGroupInfoList[]itemOptionInfoList")
                            .type(JsonFieldType.ARRAY)
                            .description("옵션 그룹 리스트"),

                        fieldWithPath("itemOptionGroupInfoList[]ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 그룹 순서"),

                        fieldWithPath("itemOptionGroupInfoList[]itemOptionInfoList[]itemOptionName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 이름"),

                        fieldWithPath("itemOptionGroupInfoList[]itemOptionInfoList[]itemOptionPrice")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 가격"),

                        fieldWithPath("itemOptionGroupInfoList[]itemOptionInfoList[]ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 순서"),

                        fieldWithPath("grayImage.path")
                            .type(JsonFieldType.STRING)
                            .description("흑백사진 주소"),

                        fieldWithPath("grayImage.url")
                            .type(JsonFieldType.STRING)
                            .description("흑백사진 url")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 아이템")
        void fail() throws Exception {
            doThrow(new ItemNotFoundException())
                .when(itemService)
                .get(any());

            mockMvc.perform(get(ITEM_DEFAULT_URL + "?name={name}", 아이템_영어_이름))
                .andDo(print())
                .andDo(document("item/get/fail/notFound",
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
    }

    @Nested
    @DisplayName("있기있는 아이템들을 조회한다")
    class getPopularItems {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(itemService.getPopularItems(any()))
                .willReturn(ITEM_아이템_응답());

            mockMvc.perform(get(ITEM_DEFAULT_URL + "/popular"))
                .andDo(print())
                .andDo(document("item/getPopularItems",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    responseFields(ITEM_리스트_응답_필드())
                ))
                .andExpect(status().isOk());
        }
    }
}
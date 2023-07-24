package com.rudkids.api.product;

import com.rudkids.api.common.ControllerTest;
import com.rudkids.core.product.exception.ProductNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.api.common.fixtures.product.ProductFixturesAndDocs.*;
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

class ProductControllerTest extends ControllerTest {

    @Nested
    @DisplayName("프로덕트들을 조회한다")
    class getAll {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(productService.getAll(any()))
                .willReturn(PRODUCT_리스트_조회_응답());

            mockMvc.perform(get(PRODUCT_DEFAULT_URL))
                .andDo(print())
                .andDo(document("product/getAll",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    responseFields(PRODUCT_리스트_응답_필드())))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("프로덕트를 상세조회한다")
    class get {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(productService.get(any(), any()))
                .willReturn(PRODUCT_상세조회_INFO());

            mockMvc.perform(get(PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디))
                .andDo(print())
                .andDo(document("product/get",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                            parameterWithName("id")
                                .description("프로덕트 id")
                        ),
                        responseFields(
                            fieldWithPath("title")
                                .type(JsonFieldType.STRING)
                                .description("제목"),

                            fieldWithPath("bio")
                                .type(JsonFieldType.STRING)
                                .description("소개글"),

                            fieldWithPath("frontImage")
                                .type(JsonFieldType.OBJECT)
                                .description("프로덕트 앞 이미지"),

                            fieldWithPath("frontImage.path")
                                .type(JsonFieldType.STRING)
                                .description("프로덕트 앞 이미지 path"),

                            fieldWithPath("frontImage.url")
                                .type(JsonFieldType.STRING)
                                .description("프로덕트 앞 이미지 url"),

                            fieldWithPath("backImage")
                                .type(JsonFieldType.OBJECT)
                                .description("프로덕트 뒤 이미지"),

                            fieldWithPath("backImage.path")
                                .type(JsonFieldType.STRING)
                                .description("프로덕트 뒤 이미지 path"),

                            fieldWithPath("backImage.url")
                                .type(JsonFieldType.STRING)
                                .description("프로덕트 뒤 이미지 url"),

                            fieldWithPath("bannerImages")
                                .type(JsonFieldType.ARRAY)
                                .description("프로덕트 배너 이미지들"),

                            fieldWithPath("bannerImages[]path")
                                .type(JsonFieldType.STRING)
                                .description("프로덕트 배너 이미지 path"),

                            fieldWithPath("bannerImages[]url")
                                .type(JsonFieldType.STRING)
                                .description("프로덕트 배너 이미지 url"),

                            fieldWithPath("bannerImages[]ordering")
                                .type(JsonFieldType.NUMBER)
                                .description("프로덕트 배너 이미지 순서"),

                            fieldWithPath("items.content")
                                .type(JsonFieldType.ARRAY)
                                .description("아이템들"),

                            fieldWithPath("items.content[].itemId")
                                .type(JsonFieldType.STRING)
                                .description("아이템 id"),

                            fieldWithPath("items.content[].name")
                                .type(JsonFieldType.STRING)
                                .description("아이템 이름"),

                            fieldWithPath("items.content[].price")
                                .type(JsonFieldType.NUMBER)
                                .description("아이템 가격"),

                            fieldWithPath("items.content[].imageUrls")
                                .type(JsonFieldType.ARRAY)
                                .description("여러 이미지 url"),

                            fieldWithPath("items.content[].itemStatus")
                                .type(JsonFieldType.STRING)
                                .description("아이템 상태"),

                            fieldWithPath("items.pageable")
                                .type(JsonFieldType.STRING)
                                .description("페이징 응답"),

                            fieldWithPath("items.last")
                                .type(JsonFieldType.BOOLEAN)
                                .description("마지막 페이지 인지 아닌지 여부"),

                            fieldWithPath("items.totalPages")
                                .type(JsonFieldType.NUMBER)
                                .description("총 페이지 수"),

                            fieldWithPath("items.totalElements")
                                .type(JsonFieldType.NUMBER)
                                .description("프로덕트 전체 데이터 갯수"),

                            fieldWithPath("items.first")
                                .type(JsonFieldType.BOOLEAN)
                                .description("첫번째 페이지 인지 여부"),

                            fieldWithPath("items.size")
                                .type(JsonFieldType.NUMBER)
                                .description("페이지 당 나타낼 수 있는 데이터의 갯수"),

                            fieldWithPath("items.number")
                                .type(JsonFieldType.NUMBER)
                                .description("현재 페이지의 번호"),

                            fieldWithPath("items.sort")
                                .type(JsonFieldType.OBJECT)
                                .description("정렬 값들"),

                            fieldWithPath("items.sort.empty")
                                .type(JsonFieldType.BOOLEAN)
                                .description("정렬 값이 비어있는지 여부"),

                            fieldWithPath("items.sort.sorted")
                                .type(JsonFieldType.BOOLEAN)
                                .description("정렬 했는지 여부"),

                            fieldWithPath("items.sort.unsorted")
                                .type(JsonFieldType.BOOLEAN)
                                .description("정렬 하지 않았는지 여부"),

                            fieldWithPath("items.numberOfElements")
                                .type(JsonFieldType.NUMBER)
                                .description("실제 데이터의 갯수"),

                            fieldWithPath("items.empty")
                                .type(JsonFieldType.BOOLEAN)
                                .description("리스트가 비어있는지 여부")
                        )
                    )
                )
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 프로덕트")
        void fail() throws Exception {
            doThrow(new ProductNotFoundException())
                .when(productService)
                .get(any(), any());

            mockMvc.perform(get(PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디))
                .andDo(print())
                .andDo(document("product/get/fail/notFound",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                            parameterWithName("id")
                                .description("존재하지 않는 프로덕트 id")
                        )
                    )
                )
                .andExpect(status().isNotFound());
        }
    }
}
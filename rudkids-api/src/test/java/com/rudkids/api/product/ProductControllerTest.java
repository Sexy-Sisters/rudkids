package com.rudkids.api.product;

import com.rudkids.api.common.ControllerTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.api.common.fixtures.ProductControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest extends ControllerTest {

    @Disabled("api response와 restdoc response가 다르다")
    @DisplayName("[프로덕트-전체조회]")
    @Test
    void 프로덕트_리스트를_조회한다() throws Exception {
        given(productService.getAll(any()))
            .willReturn(PRODUCT_리스트_조회_응답());

        mockMvc.perform(get(PRODUCT_DEFAULT_URL))
            .andDo(print())
            .andDo(document("product/findAll",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("content[].productId")
                        .type(JsonFieldType.STRING)
                        .description("프로덕트 아이디"),

                    fieldWithPath("content[].title")
                        .type(JsonFieldType.STRING)
                        .description("매거진 제목"),

                    fieldWithPath("content[].frontImageUrl")
                        .type(JsonFieldType.STRING)
                        .description("프로덕트 앞 이미지"),

                    fieldWithPath("content[].backImageUrl")
                        .type(JsonFieldType.STRING)
                        .description("프로덕트 뒤 이미지"),

                    fieldWithPath("content[].status")
                        .type(JsonFieldType.STRING)
                        .description("프로덕트 상태"),

                    fieldWithPath("pageable")
                        .type(JsonFieldType.STRING)
                        .description("페이징 응답"),

                    fieldWithPath("last")
                        .type(JsonFieldType.BOOLEAN)
                        .description("마지막 페이지 인지 아닌지 여부"),

                    fieldWithPath("totalPages")
                        .type(JsonFieldType.NUMBER)
                        .description("총 페이지 수"),

                    fieldWithPath("totalElements")
                        .type(JsonFieldType.NUMBER)
                        .description("프로덕트 전체 데이터 갯수"),

                    fieldWithPath("first")
                        .type(JsonFieldType.BOOLEAN)
                        .description("첫번째 페이지 인지 여부"),

                    fieldWithPath("size")
                        .type(JsonFieldType.NUMBER)
                        .description("페이지 당 나타낼 수 있는 데이터의 갯수"),

                    fieldWithPath("number")
                        .type(JsonFieldType.NUMBER)
                        .description("현재 페이지의 번호"),

                    fieldWithPath("sort")
                        .type(JsonFieldType.OBJECT)
                        .description("정렬 값들"),

                    fieldWithPath("sort.empty")
                        .type(JsonFieldType.BOOLEAN)
                        .description("정렬 값이 비어있는지 여부"),

                    fieldWithPath("sort.sorted")
                        .type(JsonFieldType.BOOLEAN)
                        .description("정렬 했는지 여부"),

                    fieldWithPath("sort.unsorted")
                        .type(JsonFieldType.BOOLEAN)
                        .description("정렬 하지 않았는지 여부"),

                    fieldWithPath("numberOfElements")
                        .type(JsonFieldType.NUMBER)
                        .description("실제 데이터의 갯수"),

                    fieldWithPath("empty")
                        .type(JsonFieldType.BOOLEAN)
                        .description("리스트가 비어있는지 여부")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[프로덕트-상세조회]")
    @Test
    void 프로덕트를_상세조회한다() throws Exception {
        given(productService.get(any(), any()))
            .willReturn(PRODUCT_상세조회_INFO());

        mockMvc.perform(get(PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디))
            .andDo(print())
            .andDo(document("product/find",
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

                        fieldWithPath("frontImageUrl")
                            .type(JsonFieldType.STRING)
                            .description("프로덕트 앞 이미지"),

                        fieldWithPath("backImageUrl")
                            .type(JsonFieldType.STRING)
                            .description("프로덕트 뒤 이미지"),

                        fieldWithPath("bannerImageUrls")
                            .type(JsonFieldType.ARRAY)
                            .description("프로덕트 배너 이미지 urls"),

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

    @DisplayName("[프로덕트-검색]")
    @Test
    void 제목으로_프로덕트를_검색한다() throws Exception {
        given(productService.search(any()))
            .willReturn(PRODUCT_검색_INFO_응답());

        mockMvc.perform(get(PRODUCT_DEFAULT_URL + "/search?title={title}", 프로덕트_제목))
            .andDo(print())
            .andDo(document("product/search",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    queryParameters(
                        parameterWithName("title")
                            .description("프로덕트 제목")
                    ),
                    responseFields(
                        fieldWithPath("[]productId")
                            .type(JsonFieldType.STRING)
                            .description("프로덕트 아이티"),

                        fieldWithPath("[]title")
                            .type(JsonFieldType.STRING)
                            .description("프로덕트 제목"),

                        fieldWithPath("[]frontImageUrl")
                            .type(JsonFieldType.STRING)
                            .description("프로덕트 앞 이미지")
                    )
                )
            )
            .andExpect(status().isOk());
    }
}
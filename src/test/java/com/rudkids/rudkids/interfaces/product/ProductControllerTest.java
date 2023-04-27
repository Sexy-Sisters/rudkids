package com.rudkids.rudkids.interfaces.product;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.product.ProductInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.product.ProductControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest extends ControllerTest {

    @DisplayName("[프로덕트-전체조회]")
    @Test
    void 프로덕트_리스트를_조회한다() throws Exception {
        given(productService.findAll())
            .willReturn(PRODUCT_리스트_조회_응답());

        given(productDtoMapper.toResponse(PRODUCT_MAIN_INFO()))
            .willReturn(PRODUCT_MAIN_RESPONSE());

        mockMvc.perform(get(PRODUCT_DEFAULT_URL))
            .andDo(print())
            .andDo(document("product/findAll",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("[].productId")
                        .type(JsonFieldType.STRING)
                        .description("프로덕트 아이디"),

                    fieldWithPath("[].title")
                        .type(JsonFieldType.STRING)
                        .description("매거진 제목"),

                    fieldWithPath("[].frontImageUrl")
                        .type(JsonFieldType.STRING)
                        .description("프로덕트 앞 이미지"),

                    fieldWithPath("[].backImageUrl")
                        .type(JsonFieldType.STRING)
                        .description("프로덕트 뒤 이미지"),

                    fieldWithPath("[].status")
                        .type(JsonFieldType.STRING)
                        .description("프로덕트 상태")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[프로덕트-상세조회]")
    @Test
    void findTest() throws Exception {
        given(productService.find(any()))
            .willReturn(PRODUCT_상세조회_INFO());

        given(productDtoMapper.toResponse((ProductInfo.Detail) any()))
            .willReturn(PRODUCT_상세조회_RESPONSE());

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

                        fieldWithPath("items[].itemId")
                            .type(JsonFieldType.STRING)
                            .description("아이템 id"),

                        fieldWithPath("items[].name")
                            .type(JsonFieldType.STRING)
                            .description("아이템 이름"),

                        fieldWithPath("items[].price")
                            .type(JsonFieldType.NUMBER)
                            .description("아이템 가격"),

                        fieldWithPath("items[].imageUrls")
                            .type(JsonFieldType.ARRAY)
                            .description("여러 이미지 url"),

                        fieldWithPath("items[].itemStatus")
                            .type(JsonFieldType.STRING)
                            .description("아이템 상태")
                    )
                )
            )
            .andExpect(status().isOk());
    }
}
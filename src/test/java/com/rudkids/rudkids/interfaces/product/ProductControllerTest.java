package com.rudkids.rudkids.interfaces.product;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.product.ProductInfo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.product.ProductControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
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

class ProductControllerTest extends ControllerTest {

    @Disabled("MockMultipartFile 오류 잡고 나서 테스트 코드 실행")
    @DisplayName("프로덕트를 등록한다.")
    @Test
    void createTest() throws Exception {
        willDoNothing()
            .given(productService)
            .create(any(), any());

        mockMvc.perform(post(PRODUCT_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.MULTIPART_FORM_DATA)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content(objectMapper.writeValueAsString(PRODUCT_등록_요청()))
            )
            .andDo(print())
            .andDo(document("product/create",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                requestFields(
                    fieldWithPath("title")
                        .type(JsonFieldType.STRING)
                        .description("제목"),

                    fieldWithPath("productBio")
                        .type(JsonFieldType.STRING)
                        .description("소개글"),

                    fieldWithPath("frontImage")
                        .type(JsonFieldType.STRING)
                        .description("앞 이미지"),

                    fieldWithPath("backImage")
                        .type(JsonFieldType.STRING)
                        .description("뒤 이미지")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("프로덕트 리스트를 조회한다.")
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

    @DisplayName("프로덕트 세부사항을 조회한다.")
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

    @DisplayName("프로덕트 리스트를 조회한다.")
    @Test
    void findAllTest() throws Exception {
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

                    fieldWithPath("[].productBio")
                        .type(JsonFieldType.STRING)
                        .description("프로덕트 소개글")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("프로덕트를 수정한다.")
    @Test
    void updateTest() throws Exception {
        willDoNothing()
            .given(productService)
            .update(any(), any(), any());

        mockMvc.perform(put(PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_수정_요청()))
            )
            .andDo(print())
            .andDo(document("product/update",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("프로덕트 id")
                    ),
                    requestFields(
                        fieldWithPath("title")
                            .type(JsonFieldType.STRING)
                            .description("제목"),

                        fieldWithPath("productBio")
                            .type(JsonFieldType.STRING)
                            .description("소개글")

                    )
                )
            )
            .andExpect(status().isOk());
    }

    @DisplayName("프로덕트를 삭제한다.")
    @Test
    void deleteTest() throws Exception {
        willDoNothing()
            .given(productService)
            .delete(any(), any());

        mockMvc.perform(delete(PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
            )
            .andDo(print())
            .andDo(document("product/delete",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("프로덕트 id")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("프로덕트 상태를 변경한다.")
    @Test
    void changeStatusTest() throws Exception {
        willDoNothing()
            .given(productService)
            .changeStatus(any(), any(), any());

        mockMvc.perform(put(PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_상태_변경_요청()))
            )
            .andDo(print())
            .andDo(document("product/changeStatus",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("프로덕트 id")
                ),
                requestFields(
                    fieldWithPath("productStatus")
                        .type(JsonFieldType.STRING)
                        .description("프로덕트 상태")
                )
            ))
            .andExpect(status().isOk());
    }
}
package com.rudkids.rudkids.interfaces.product;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.product.exception.ProductNotFoundException;
import com.rudkids.rudkids.domain.user.exception.NotAdminRoleException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.product.ProductControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerFailTest extends ControllerTest {

    @Disabled("MockMultipartFile 오류 잡고 나서 테스트 코드 실행")
    @DisplayName("[생성-403] 관리자가 아닌 유저가 프로덕트를 생성하면 403을 반환한다.")
    @Test
    void 관리자가_아닌_유저가_프로덕트를_등록하면_403을_반환한다() throws Exception {
        doThrow(new NotAdminRoleException())
            .when(productService)
            .create(any(), any());

        mockMvc.perform(post(PRODUCT_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_등록_요청()))
            )
            .andDo(print())
            .andDo(document("product/create/failByForbidden",
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
                        .description("소개글")
                )
            ))
            .andExpect(status().isForbidden());
    }

    @DisplayName("[상세조회-404] 존재하지 않는 프로덕트의 세부사항을 조회할 경우 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_프로덕트의_세부사항을_조회할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new ProductNotFoundException())
            .when(productService)
            .find(any());

        mockMvc.perform(get(PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디))
            .andDo(print())
            .andDo(document("product/find/failByNotFoundError",
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

    @DisplayName("[수정-403] 관리자가 아닌 유저가 프로덕트를 수정 시 상태코드 403을 반환한다.")
    @Test
    void 관리자가_아닌_유저가_프로덕트를_수정_시_상태코드_403을_반환한다() throws Exception {
        doThrow(new NotAdminRoleException())
            .when(productService)
            .update(any(), any(), any());

        mockMvc.perform(put(PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_수정_요청()))
            )
            .andDo(print())
            .andDo(document("product/update/failByForbidden",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("존재하지 않는 프로덕트 id")
                ),
                requestFields(
                    fieldWithPath("title")
                        .type(JsonFieldType.STRING)
                        .description("제목"),

                    fieldWithPath("productBio")
                        .type(JsonFieldType.STRING)
                        .description("소개글")
                ),
                responseFields(
                    fieldWithPath("message")
                        .type(JsonFieldType.STRING)
                        .description("에러 메세지")
                )
            ))
            .andExpect(status().isForbidden());
    }

    @DisplayName("[수정-404] 존재하지 않는 프로덕트를 수정 시 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_프로덕트를_수정_시_상태코드_404를_반환한다() throws Exception {
        doThrow(new ProductNotFoundException())
            .when(productService)
            .update(any(), any(), any());

        mockMvc.perform(put(PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_수정_요청()))
            )
            .andDo(print())
            .andDo(document("product/update/failByNotFoundError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("존재하지 않는 프로덕트 id")
                ),
                requestFields(
                    fieldWithPath("title")
                        .type(JsonFieldType.STRING)
                        .description("제목"),

                    fieldWithPath("productBio")
                        .type(JsonFieldType.STRING)
                        .description("소개글")
                ),
                responseFields(
                    fieldWithPath("message")
                        .type(JsonFieldType.STRING)
                        .description("에러 메세지")
                )
            ))
            .andExpect(status().isNotFound());
    }

    @DisplayName("[삭제-403] 관리자가 아닌 유저가 프로덕트를 삭제 시 상태코드 403을 반환한다.")
    @Test
    void 관리자가_아닌_유저가_프로덕트를_삭제_시_상태코드_403을_반환한다() throws Exception {
        doThrow(new NotAdminRoleException())
            .when(productService)
            .delete(any(), any());

        mockMvc.perform(delete(PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
            )
            .andDo(print())
            .andDo(document("product/delete/failByForbiddenError",
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
            .andExpect(status().isForbidden());
    }

    @DisplayName("[삭제-404] 존재하지 않는 프로덕트를 삭제 시 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_프로덕트를_삭제_시_상태코드_404를_반환한다() throws Exception {
        doThrow(new ProductNotFoundException())
            .when(productService)
            .delete(any(), any());

        mockMvc.perform(delete(PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
            )
            .andDo(print())
            .andDo(document("product/delete/failByNotFoundError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("존재하지 않는 프로덕트 id")
                )
            ))
            .andExpect(status().isNotFound());
    }

    @DisplayName("[상태변경-403] 관리자가 아닌 유저가 프로덕트의 상태를 변경 시 상태코드 403을 반환한다.")
    @Test
    void 관리자가_아닌_유저가_프로덕트의_상태를_변경_시_상태코드_403을_반환한다() throws Exception {
        doThrow(new NotAdminRoleException())
            .when(productService)
            .changeStatus(any(), any(), any());

        mockMvc.perform(put(PRODUCT_DEFAULT_URL + "/status/{id}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_상태_변경_요청()))
            )
            .andDo(print())
            .andDo(document("product/changeStatus/failByNotFoundError",
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
            .andExpect(status().isForbidden());
    }

    @DisplayName("[상태변경-404] 존재하지 않는 프로덕트의 상태를 변경 시 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_프로덕트의_상태를_변경_시_상태코드_404를_반환한다() throws Exception {
        doThrow(new ProductNotFoundException())
            .when(productService)
            .changeStatus(any(), any(), any());

        mockMvc.perform(put(PRODUCT_DEFAULT_URL + "/status/{id}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_상태_변경_요청()))
            )
            .andDo(print())
            .andDo(document("product/changeStatus/failByNotFoundError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("존재하지 않는 프로덕트 id")
                ),
                requestFields(
                    fieldWithPath("productStatus")
                        .type(JsonFieldType.STRING)
                        .description("프로덕트 상태")
                )
            ))
            .andExpect(status().isNotFound());
    }
}

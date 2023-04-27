package com.rudkids.rudkids.interfaces.admin;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.magazine.exception.MagazineNotFoundException;
import com.rudkids.rudkids.domain.product.exception.ProductNotFoundException;
import com.rudkids.rudkids.domain.user.exception.NotAdminRoleException;
import com.rudkids.rudkids.domain.user.exception.NotFoundUserException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.admin.AdminControllerFixtures.*;
import static com.rudkids.rudkids.common.fixtures.magazine.MagazineControllerFixtures.*;
import static com.rudkids.rudkids.common.fixtures.product.ProductControllerFixtures.*;
import static com.rudkids.rudkids.common.fixtures.product.ProductControllerFixtures.PRODUCT_상태_변경_요청;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminControllerFailTest extends ControllerTest {

    @DisplayName("[유저-검색-403-에러]")
    @Test
    void 어드민_권한이_아닌_사용자가_유저를_검색할_경우_상태코드_403을_반환한다() throws Exception {
        doThrow(new NotAdminRoleException())
            .when(adminService)
            .searchUser(any(), any());

        mockMvc.perform(get(ADMIN_USER_DEFAULT_URL + "?email={email}", USER_EMAIL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
            .andDo(print())
            .andDo(document("admin/searchUser/failByForbiddenError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("어드민 권한이 아닌 사용자의 JWT Access Token")
                ),
                queryParameters(
                    parameterWithName("email")
                        .description("유저 이메일")
                )
            ))
            .andExpect(status().isForbidden());
    }

    @DisplayName("[유저-검색-404-에러]")
    @Test
    void 존재하지_않는_유저를_검색할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new NotFoundUserException())
            .when(adminService)
            .searchUser(any(), any());

        mockMvc.perform(get(ADMIN_USER_DEFAULT_URL + "?email={email}", USER_EMAIL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
            .andDo(print())
            .andDo(document("admin/searchUser/failByNotFoundError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("어드민 권한이 아닌 사용자의 JWT Access Token")
                ),
                queryParameters(
                    parameterWithName("email")
                        .description("존재하지 않는 유저 이메일")
                )
            ))
            .andExpect(status().isNotFound());
    }

    @DisplayName("[유저-권한변경-403-에러]")
    @Test
    void 어드민_권한이_아닌_사용자가_유저의_권한을_변경할_경우_상태코드_403을_반환한다() throws Exception {
        doThrow(new NotAdminRoleException())
            .when(adminService)
            .changeUserRole(any(), any(), any());

        mockMvc.perform(patch(ADMIN_USER_DEFAULT_URL + "/{id}", USER_ID)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(유저_권한_변경_요청())))
            .andDo(print())
            .andDo(document("admin/changeUserRole/failByForbiddenError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("어드민 권한이 아닌 사용자의 JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("유저 id")
                ),
                requestFields(
                    fieldWithPath("role")
                        .type(JsonFieldType.STRING)
                        .description("유저 권한")
                )
            ))
            .andExpect(status().isForbidden());
    }

    @DisplayName("[유저-권한변경-404-에러")
    @Test
    void 존재하지_않는_유저의_권한을_변경할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new NotFoundUserException())
            .when(adminService)
            .changeUserRole(any(), any(), any());

        mockMvc.perform(patch(ADMIN_USER_DEFAULT_URL + "/{id}", USER_ID)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(유저_권한_변경_요청())))
            .andDo(print())
            .andDo(document("admin/changeUserRole/failByNotFoundError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("어드민 권한이 아닌 사용자의 JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("존재하지 않는 유저 id")
                ),
                requestFields(
                    fieldWithPath("role")
                        .type(JsonFieldType.STRING)
                        .description("유저 권한")
                )
            ))
            .andExpect(status().isNotFound());
    }

    @Disabled("MockMultipartFile 오류 잡고 나서 테스트 코드 실행")
    @DisplayName("[프로덕트-생성-403-에러]")
    @Test
    void 관리자가_아닌_유저가_프로덕트를_등록하면_403을_반환한다() throws Exception {
        doThrow(new NotAdminRoleException())
            .when(productService)
            .create(any(), any());

        mockMvc.perform(post(ADMIN_PRODUCT_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_등록_요청()))
            )
            .andDo(print())
            .andDo(document("product/create/failByForbiddenError",
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

    @DisplayName("[프로덕트-상태변경-403-에러]")
    @Test
    void 관리자가_아닌_유저가_프로덕트의_상태를_변경_시_상태코드_403을_반환한다() throws Exception {
        doThrow(new NotAdminRoleException())
            .when(productService)
            .changeStatus(any(), any(), any());

        mockMvc.perform(patch(ADMIN_PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_상태_변경_요청()))
            )
            .andDo(print())
            .andDo(document("product/changeStatus/failByForbiddenError",
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

    @DisplayName("[프로덕트-상태변경-404-에러]")
    @Test
    void 존재하지_않는_프로덕트의_상태를_변경_시_상태코드_404를_반환한다() throws Exception {
        doThrow(new ProductNotFoundException())
            .when(productService)
            .changeStatus(any(), any(), any());

        mockMvc.perform(patch(ADMIN_PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디)
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

    @DisplayName("[프로덕트-수정-403-에러]")
    @Test
    void 관리자가_아닌_유저가_프로덕트를_수정_시_상태코드_403을_반환한다() throws Exception {
        doThrow(new NotAdminRoleException())
            .when(productService)
            .update(any(), any(), any());

        mockMvc.perform(put(ADMIN_PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디)
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

    @DisplayName("[프로덕트-수정-404-에러]")
    @Test
    void 존재하지_않는_프로덕트를_수정_시_상태코드_404를_반환한다() throws Exception {
        doThrow(new ProductNotFoundException())
            .when(productService)
            .update(any(), any(), any());

        mockMvc.perform(put(ADMIN_PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디)
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

    @DisplayName("[프로덕트-삭제-403-에러]")
    @Test
    void 관리자가_아닌_유저가_프로덕트를_삭제_시_상태코드_403을_반환한다() throws Exception {
        doThrow(new NotAdminRoleException())
            .when(productService)
            .delete(any(), any());

        mockMvc.perform(delete(ADMIN_PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디)
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

    @DisplayName("[프로덕트-삭제-404-에러]")
    @Test
    void 존재하지_않는_프로덕트를_삭제_시_상태코드_404를_반환한다() throws Exception {
        doThrow(new ProductNotFoundException())
            .when(productService)
            .delete(any(), any());

        mockMvc.perform(delete(ADMIN_PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디)
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

    @DisplayName("[매거진-생성-403-에러]")
    @Test
    void 어드민_권한이_아닌_사용자가_매거진을_작성할_경우_상태코드_403을_반환한다() throws Exception {
        doThrow(new NotAdminRoleException())
            .when(magazineService)
            .create(any(), any());

        mockMvc.perform(post(ADMIN_MAGAZINE_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(MAGAZINE_작성_요청())))
            .andDo(print())
            .andDo(document("magazine/create/failForbiddenError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("어드민 권한이 아닌 사용자의 JWT Access Token")
                ),
                requestFields(
                    fieldWithPath("title")
                        .type(JsonFieldType.STRING)
                        .description("제목"),

                    fieldWithPath("content")
                        .type(JsonFieldType.STRING)
                        .description("내용")
                )
            ))
            .andExpect(status().isForbidden());
    }

    @DisplayName("[매거진-수정-403-에러]")
    @Test
    void 어드민_권한이_아닌_사용자가_매거진을_수정할_경우_상태코드_403를_반환한다() throws Exception {
        doThrow(new NotAdminRoleException())
            .when(magazineService)
            .update(any(), any(), any());

        mockMvc.perform(put(ADMIN_MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(MAGAZINE_수정_요청())))
            .andDo(print())
            .andDo(document("magazine/update/failForbiddenError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("어드민 권한이 아닌 사용자의 JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("매거진 id")
                ),
                requestFields(
                    fieldWithPath("title")
                        .type(JsonFieldType.STRING)
                        .description("새로운 제목"),

                    fieldWithPath("content")
                        .type(JsonFieldType.STRING)
                        .description("새로운 내용")
                )
            ))
            .andExpect(status().isForbidden());
    }

    @DisplayName("[매거진-수정-404-에러]")
    @Test
    void 존재하지_않는_매거진을_수정할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new MagazineNotFoundException())
            .when(magazineService)
            .update(any(), any(), any());

        mockMvc.perform(put(ADMIN_MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(MAGAZINE_수정_요청())))
            .andDo(print())
            .andDo(document("magazine/update/failByNotFoundError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("존재하지 않는 매거진 id")
                ),
                requestFields(
                    fieldWithPath("title")
                        .type(JsonFieldType.STRING)
                        .description("새로운 제목"),

                    fieldWithPath("content")
                        .type(JsonFieldType.STRING)
                        .description("새로운 내용")
                )
            ))
            .andExpect(status().isNotFound());
    }

    @DisplayName("[매거진-삭제-403-에러]")
    @Test
    void 어드민_권한이_아닌_사용자가_매거진을_삭제할_경우_상태코드_403를_반환한다() throws Exception {
        doThrow(new NotAdminRoleException())
            .when(magazineService)
            .delete(any(), any());

        mockMvc.perform(delete(ADMIN_MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
            .andDo(print())
            .andDo(document("magazine/delete/failByForbiddenError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("어드민 권한이 아닌 사용자의 JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("존재하지 않는 매거진 id")
                )
            ))
            .andExpect(status().isForbidden());
    }

    @DisplayName("[매거진-삭제-404-에러]")
    @Test
    void 존재하지_않는_매거진을_삭제할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new MagazineNotFoundException())
            .when(magazineService)
            .delete(any(), any());

        mockMvc.perform(delete(ADMIN_MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
            .andDo(print())
            .andDo(document("magazine/delete/failByNotFoundError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("존재하지 않는 매거진 id")
                )
            ))
            .andExpect(status().isNotFound());
    }
}

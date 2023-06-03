package com.rudkids.rudkids.interfaces.admin;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.order.exception.OrderStatusNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.headers.HeaderDescriptor;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static com.rudkids.rudkids.common.fixtures.admin.AdminControllerFixtures.*;
import static com.rudkids.rudkids.common.fixtures.magazine.MagazineControllerFixtures.*;
import static com.rudkids.rudkids.common.fixtures.order.OrderControllerFixtures.*;
import static com.rudkids.rudkids.common.fixtures.product.ProductControllerFixtures.*;
import static com.rudkids.rudkids.common.fixtures.product.ProductControllerFixtures.PRODUCT_상태_변경_요청;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
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

public class AdminControllerTest extends ControllerTest {

    @DisplayName("[유저-검색]")
    @Test
    void 유저를_검색한다() throws Exception {
        given(adminService.searchUser(any()))
            .willReturn(유저_정보_INFO_응답());

        mockMvc.perform(get(ADMIN_USER_DEFAULT_URL + "?email={email}", USER_EMAIL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
            .andDo(print())
            .andDo(document("admin/searchUser",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                queryParameters(
                    parameterWithName("email")
                        .description("유저 이메일")
                ),
                responseFields(
                    fieldWithPath("[]email")
                        .type(JsonFieldType.STRING)
                        .description("유저 이메일"),

                    fieldWithPath("[]name")
                        .type(JsonFieldType.STRING)
                        .description("유저 이름"),

                    fieldWithPath("[]age")
                        .type(JsonFieldType.NUMBER)
                        .description("유저 나이"),

                    fieldWithPath("[]gender")
                        .type(JsonFieldType.STRING)
                        .description("유저 성별"),

                    fieldWithPath("[]phoneNumber")
                        .type(JsonFieldType.STRING)
                        .description("유저 전화번호"),

                    fieldWithPath("[]profileImageUrl")
                        .type(JsonFieldType.STRING)
                        .description("유저 이미지"),

                    fieldWithPath("[]roleType")
                        .type(JsonFieldType.STRING)
                        .description("유저 권한")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[유저-권한변경]")
    @Test
    void 유저_권한을_변경한다() throws Exception {
        willDoNothing()
            .given(adminService)
            .changeUserRole(any(), any());

        mockMvc.perform(patch(ADMIN_USER_DEFAULT_URL + "/{id}", USER_ID)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(유저_권한_변경_요청())))
            .andDo(print())
            .andDo(document("admin/changeUserRole",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
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
            .andExpect(status().isOk());
    }

    @DisplayName("[프로덕트-생성]")
    @Test
    void 프로덕트를_생성한다() throws Exception {
        willDoNothing()
            .given(productService)
            .create(any());

        mockMvc.perform(post(ADMIN_PRODUCT_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
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

                    fieldWithPath("frontImage.path")
                        .type(JsonFieldType.STRING)
                        .description("앞 이미지 path"),

                    fieldWithPath("frontImage.url")
                        .type(JsonFieldType.STRING)
                        .description("앞 이미지 url"),

                    fieldWithPath("backImage.path")
                        .type(JsonFieldType.STRING)
                        .description("뒤 이미지 path"),

                    fieldWithPath("backImage.url")
                        .type(JsonFieldType.STRING)
                        .description("뒤 이미지 url")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[프로덕트-상태변경]")
    @Test
    void 프로덕트_상태를_변경한다() throws Exception {
        willDoNothing()
            .given(productService)
            .changeStatus(any(), any());

        mockMvc.perform(patch(ADMIN_PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디)
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

    @DisplayName("[프로덕트-수정]")
    @Test
    void updateTest() throws Exception {
        willDoNothing()
            .given(productService)
            .update(any(), any());

        mockMvc.perform(put(ADMIN_PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디)
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
                            .description("소개글"),

                        fieldWithPath("frontImage.path")
                            .type(JsonFieldType.STRING)
                            .description("앞 이미지 path"),

                        fieldWithPath("frontImage.url")
                            .type(JsonFieldType.STRING)
                            .description("앞 이미지 url"),

                        fieldWithPath("backImage.path")
                            .type(JsonFieldType.STRING)
                            .description("뒤 이미지 path"),

                        fieldWithPath("backImage.url")
                            .type(JsonFieldType.STRING)
                            .description("뒤 이미지 url")
                    )
                )
            )
            .andExpect(status().isOk());
    }

    @DisplayName("[프로덕트-삭제]")
    @Test
    void deleteTest() throws Exception {
        willDoNothing()
            .given(productService)
            .delete(any());

        mockMvc.perform(delete(ADMIN_PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디)
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

    @DisplayName("[매거진-생성]")
    @Test
    void 매거진을_작성한다() throws Exception {
        willDoNothing()
            .given(magazineService)
            .create(any());

        mockMvc.perform(post(ADMIN_MAGAZINE_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(MAGAZINE_작성_요청())))
            .andDo(print())
            .andDo(document("magazine/create",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(JWT_ACCESS_TOKEN()),
                requestFields(
                    fieldWithPath("title")
                        .type(JsonFieldType.STRING)
                        .description("제목"),

                    fieldWithPath("content")
                        .type(JsonFieldType.STRING)
                        .description("내용"),

                    fieldWithPath("writer")
                        .type(JsonFieldType.STRING)
                        .description("작성자")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[매거진-수정]")
    @Test
    void 매거진을_수정한다() throws Exception {
        willDoNothing()
            .given(magazineService)
            .update(any(), any());

        mockMvc.perform(put(ADMIN_MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(MAGAZINE_수정_요청())))
            .andDo(print())
            .andDo(document("magazine/update",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
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
                        .description("새로운 내용"),

                    fieldWithPath("writer")
                        .type(JsonFieldType.STRING)
                        .description("새로운 작성자")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[매거진-삭제]")
    @Test
    void 매거진을_삭제한다() throws Exception {
        willDoNothing()
            .given(magazineService)
            .delete(any());

        mockMvc.perform(delete(ADMIN_MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
            .andDo(print())
            .andDo(document("magazine/delete",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("매거진 id")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[주문-전체조회")
    @Test
    void 전체_주문을_조회한다() throws Exception {
        given(orderService.findAll(any()))
            .willReturn(ORDER_전체_조회_INFO());

        mockMvc.perform(get(ADMIN_ORDER_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
            .andDo(print())
            .andDo(document("order/findAll",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(JWT_ACCESS_TOKEN()),
                responseFields(ORDER_전체_주문_조회_응답_필드())
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[주문-상태변경]")
    @Test
    void 주문_상태를_변경한다() throws Exception {
        willDoNothing()
            .given(orderService)
            .changeStatus(any(), any());

        mockMvc.perform(patch(ADMIN_ORDER_DEFAULT_URL + "/{id}", ORDER_ID)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ORDER_상태변경_요청()))
            )
            .andDo(print())
            .andDo(document("order/changeStatus",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("주문 id")
                ),
                requestFields(
                    fieldWithPath("orderStatus")
                        .description("주문 상태")
                )
            ))
            .andExpect(status().isOk());
    }
}

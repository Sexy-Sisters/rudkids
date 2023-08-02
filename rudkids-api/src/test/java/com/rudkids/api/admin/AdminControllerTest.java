package com.rudkids.api.admin;

import com.rudkids.api.common.ControllerTest;
import com.rudkids.core.item.exception.ItemNotFoundException;
import com.rudkids.core.product.exception.ProductNotFoundException;
import com.rudkids.core.user.exception.NotAdminRoleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.api.common.fixtures.admin.AdminFixturesAndDocs.*;
import static com.rudkids.api.common.fixtures.item.ItemFixturesAndDocs.*;
import static com.rudkids.api.common.fixtures.order.OrderFixturesAndDocs.*;
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

    @Nested
    @DisplayName("유저정보를 검색한다")
    class searchUser {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(adminService.searchUsers(any()))
                .willReturn(유저_검색_응답());

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
    }

    @Nested
    @DisplayName("유저의 권한을 변경한다")
    class changeUserRole {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
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
                        fieldWithPath("roleType")
                            .type(JsonFieldType.STRING)
                            .description("유저 권한")
                    )
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("프로덕트를 생성한다")
    class createProduct {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(adminService.createProduct(any()))
                .willReturn(PRODUCT_ID);

            mockMvc.perform(post(ADMIN_PRODUCT_DEFAULT_URL)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(프로덕트_생성_요청()))
                )
                .andDo(print())
                .andDo(document("admin/createProduct",
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
                            .description("뒤 이미지 url"),

                        fieldWithPath("bannerImages")
                            .type(JsonFieldType.ARRAY)
                            .description("배너 이미지들"),

                        fieldWithPath("bannerImages[]path")
                            .type(JsonFieldType.STRING)
                            .description("배너 이미지 주소"),

                        fieldWithPath("bannerImages[]url")
                            .type(JsonFieldType.STRING)
                            .description("배너 이미지 url"),

                        fieldWithPath("bannerImages[]ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("배너 이미지 순서"),

                        fieldWithPath("mobileImage.path")
                            .type(JsonFieldType.STRING)
                            .description("모바일 이미지 path"),

                        fieldWithPath("mobileImage.url")
                            .type(JsonFieldType.STRING)
                            .description("모바일 이미지 url"),

                        fieldWithPath("mystery")
                            .type(JsonFieldType.BOOLEAN)
                            .description("미스테리 프로덕트 여부")
                    )
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("프로덕트의 상태를 변경한다")
    class changeProductStatus {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            willDoNothing()
                .given(adminService)
                .changeProductStatus(any(), any());

            mockMvc.perform(patch(ADMIN_PRODUCT_DEFAULT_URL + "/{id}", PRODUCT_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(PRODUCT_상태_변경_요청()))
                )
                .andDo(print())
                .andDo(document("admin/changeProductStatus",
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
                        fieldWithPath("status")
                            .type(JsonFieldType.STRING)
                            .description("프로덕트 상태")
                    )
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("프로덕트를 수정한다")
    class updateProduct {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            willDoNothing()
                .given(adminService)
                .updateProduct(any(), any());

            mockMvc.perform(put(ADMIN_PRODUCT_DEFAULT_URL + "/{id}", PRODUCT_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(PRODUCT_수정_요청()))
                )
                .andDo(print())
                .andDo(document("admin/updateProduct",
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
                                .description("뒤 이미지 url"),

                            fieldWithPath("bannerImages")
                                .type(JsonFieldType.ARRAY)
                                .description("배너 이미지"),

                            fieldWithPath("bannerImages[]path")
                                .type(JsonFieldType.STRING)
                                .description("배너 이미지 주소"),

                            fieldWithPath("bannerImages[]url")
                                .type(JsonFieldType.STRING)
                                .description("배너 이미지 url"),

                            fieldWithPath("bannerImages[]ordering")
                                .type(JsonFieldType.NUMBER)
                                .description("배너 이미지 순서"),

                            fieldWithPath("mobileImage.path")
                                .type(JsonFieldType.STRING)
                                .description("모바일 이미지 path"),

                            fieldWithPath("mobileImage.url")
                                .type(JsonFieldType.STRING)
                                .description("모바일 이미지 url")
                        )
                    )
                )
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("프로덕트를 삭제한다")
    class deleteProduct {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            willDoNothing()
                .given(adminService)
                .deleteProduct(any());

            mockMvc.perform(delete(ADMIN_PRODUCT_DEFAULT_URL + "/{id}", PRODUCT_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                )
                .andDo(print())
                .andDo(document("admin/deleteProduct",
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
    }

    @Nested
    @DisplayName("아이템을 생성한다")
    class create {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(adminService.createItem(any(), any()))
                .willReturn(아이템_영어_이름);

            mockMvc.perform(post(ADMIN_ITEM_DEFAULT_URL + "/{productId}", PRODUCT_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ITEM_등록_요청()))
                ).andDo(print())
                .andDo(document("admin/createItem",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    requestFields(
                        fieldWithPath("enName")
                            .type(JsonFieldType.STRING)
                            .description("상품 영어 이름"),

                        fieldWithPath("koName")
                            .type(JsonFieldType.STRING)
                            .description("상품 한국 이름"),

                        fieldWithPath("itemBio")
                            .type(JsonFieldType.STRING)
                            .description("소개글"),

                        fieldWithPath("status")
                            .type(JsonFieldType.STRING)
                            .description("상태"),

                        fieldWithPath("price")
                            .type(JsonFieldType.NUMBER)
                            .description("가격"),

                        fieldWithPath("quantity")
                            .type(JsonFieldType.NUMBER)
                            .description("수량"),

                        fieldWithPath("limitType")
                            .type(JsonFieldType.STRING)
                            .description("수량 한정 여부"),

                        fieldWithPath("images")
                            .type(JsonFieldType.ARRAY)
                            .description("여러 아이템 이미지"),

                        fieldWithPath("images[]path")
                            .type(JsonFieldType.STRING)
                            .description("아이템 이미지 주소"),

                        fieldWithPath("images[]url")
                            .type(JsonFieldType.STRING)
                            .description("아이템 이미지 url"),

                        fieldWithPath("images[]ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("아이템 이미지 순서"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionGroupName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 그룹 이름"),

                        fieldWithPath("itemOptionGroupInfoList[].ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 그룹 순서"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionInfoList[].itemOptionName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 이름"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionInfoList[].itemOptionPrice")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 가격"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionInfoList[].ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 순서"),

                        fieldWithPath("grayImage.path")
                            .type(JsonFieldType.STRING)
                            .description("흑백사진 주소"),

                        fieldWithPath("grayImage.url")
                            .type(JsonFieldType.STRING)
                            .description("흑백사진 url"),

                        fieldWithPath("videoUrl")
                            .type(JsonFieldType.STRING)
                            .description("비디오 url"),

                        fieldWithPath("mysteryItemName")
                            .type(JsonFieldType.STRING)
                            .description("미스테리 아이템 이름"),

                        fieldWithPath("mystery")
                            .type(JsonFieldType.BOOLEAN)
                            .description("미스테리 여부")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 어드민이 아닌 유저")
        void fail() throws Exception {
            doThrow(new NotAdminRoleException())
                .when(adminService)
                .createItem(any(), any());

            mockMvc.perform(post(ADMIN_ITEM_DEFAULT_URL + "/{productId}", PRODUCT_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ITEM_등록_요청()))
                ).andDo(print())
                .andDo(document("admin/createItem/fail/forbidden",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("productId")
                            .description("프로덕트 id")
                    ),
                    requestFields(
                        fieldWithPath("enName")
                            .type(JsonFieldType.STRING)
                            .description("상품 영어 이름"),

                        fieldWithPath("koName")
                            .type(JsonFieldType.STRING)
                            .description("상품 한국 이름"),

                        fieldWithPath("itemBio")
                            .type(JsonFieldType.STRING)
                            .description("소개글"),

                        fieldWithPath("status")
                            .type(JsonFieldType.STRING)
                            .description("상태"),

                        fieldWithPath("price")
                            .type(JsonFieldType.NUMBER)
                            .description("가격"),

                        fieldWithPath("quantity")
                            .type(JsonFieldType.NUMBER)
                            .description("수량"),

                        fieldWithPath("limitType")
                            .type(JsonFieldType.STRING)
                            .description("수량 한정 여부"),

                        fieldWithPath("images")
                            .type(JsonFieldType.ARRAY)
                            .description("여러 아이템 이미지"),

                        fieldWithPath("images[]path")
                            .type(JsonFieldType.STRING)
                            .description("아이템 이미지 주소"),

                        fieldWithPath("images[]url")
                            .type(JsonFieldType.STRING)
                            .description("아이템 이미지 url"),

                        fieldWithPath("images[]ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("아이템 이미지 순서"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionGroupName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 그룹 이름"),

                        fieldWithPath("itemOptionGroupInfoList[].ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 그룹 순서"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionInfoList[].itemOptionName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 이름"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionInfoList[].itemOptionPrice")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 가격"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionInfoList[].ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 순서"),

                        fieldWithPath("grayImage.path")
                            .type(JsonFieldType.STRING)
                            .description("흑백사진 주소"),

                        fieldWithPath("grayImage.url")
                            .type(JsonFieldType.STRING)
                            .description("흑백사진 url"),

                        fieldWithPath("videoUrl")
                            .type(JsonFieldType.STRING)
                            .description("비디오 url"),

                        fieldWithPath("mysteryItemName")
                            .type(JsonFieldType.STRING)
                            .description("미스테리 아이템 이름"),

                        fieldWithPath("mystery")
                            .type(JsonFieldType.BOOLEAN)
                            .description("미스테리 여부")
                    )
                ))
                .andExpect(status().isForbidden());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 프로덕트")
        void fail2() throws Exception {
            doThrow(new ProductNotFoundException())
                .when(adminService)
                .createItem(any(), any());

            mockMvc.perform(post(ADMIN_ITEM_DEFAULT_URL + "/{productId}", PRODUCT_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ITEM_등록_요청()))
                ).andDo(print())
                .andDo(document("admin/createItem/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("productId")
                            .description("존재하지 않는 프로덕트 id")
                    ),
                    requestFields(
                        fieldWithPath("enName")
                            .type(JsonFieldType.STRING)
                            .description("상품 영어 이름"),

                        fieldWithPath("koName")
                            .type(JsonFieldType.STRING)
                            .description("상품 한국 이름"),

                        fieldWithPath("itemBio")
                            .type(JsonFieldType.STRING)
                            .description("소개글"),

                        fieldWithPath("status")
                            .type(JsonFieldType.STRING)
                            .description("상태"),

                        fieldWithPath("price")
                            .type(JsonFieldType.NUMBER)
                            .description("가격"),

                        fieldWithPath("quantity")
                            .type(JsonFieldType.NUMBER)
                            .description("수량"),

                        fieldWithPath("limitType")
                            .type(JsonFieldType.STRING)
                            .description("수량 한정 여부"),

                        fieldWithPath("images")
                            .type(JsonFieldType.ARRAY)
                            .description("여러 아이템 이미지"),

                        fieldWithPath("images[]path")
                            .type(JsonFieldType.STRING)
                            .description("아이템 이미지 주소"),

                        fieldWithPath("images[]url")
                            .type(JsonFieldType.STRING)
                            .description("아이템 이미지 url"),

                        fieldWithPath("images[]ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("아이템 이미지 순서"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionGroupName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 그룹 이름"),

                        fieldWithPath("itemOptionGroupInfoList[].ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 그룹 순서"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionInfoList[].itemOptionName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 이름"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionInfoList[].itemOptionPrice")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 가격"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionInfoList[].ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 순서"),

                        fieldWithPath("grayImage.path")
                            .type(JsonFieldType.STRING)
                            .description("흑백사진 주소"),

                        fieldWithPath("grayImage.url")
                            .type(JsonFieldType.STRING)
                            .description("흑백사진 url"),

                        fieldWithPath("videoUrl")
                            .type(JsonFieldType.STRING)
                            .description("비디오 url"),

                        fieldWithPath("mysteryItemName")
                            .type(JsonFieldType.STRING)
                            .description("미스테리 아이템 이름"),

                        fieldWithPath("mystery")
                            .type(JsonFieldType.BOOLEAN)
                            .description("미스테리 여부")
                    )
                ))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("아이템정보를 수정한다")
    class update {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            willDoNothing()
                .given(adminService)
                .updateItem(any(), any());

            mockMvc.perform(delete(ADMIN_ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ITEM_수정_요청()))
                )
                .andDo(print())
                .andDo(document("admin/updateItem",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("아이템 ID")
                    ),
                    requestFields(
                        fieldWithPath("enName")
                            .type(JsonFieldType.STRING)
                            .description("상품 영어 이름"),

                        fieldWithPath("koName")
                            .type(JsonFieldType.STRING)
                            .description("상품 한국 이름"),

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

                        fieldWithPath("images")
                            .type(JsonFieldType.ARRAY)
                            .description("여러 아이템 이미지"),

                        fieldWithPath("images[].path")
                            .type(JsonFieldType.STRING)
                            .description("아이템 이미지 주소"),

                        fieldWithPath("images[].url")
                            .type(JsonFieldType.STRING)
                            .description("아이템 이미지 url"),

                        fieldWithPath("images[].ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("아이템 이미지 순서"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionGroupName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 그룹 이름"),

                        fieldWithPath("itemOptionGroupInfoList[].ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 그룹 순서"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionInfoList[].itemOptionName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 이름"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionInfoList[].itemOptionPrice")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 가격"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionInfoList[].ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 순서"),

                        fieldWithPath("grayImage.path")
                            .type(JsonFieldType.STRING)
                            .description("흑백사진 주소"),

                        fieldWithPath("grayImage.url")
                            .type(JsonFieldType.STRING)
                            .description("흑백사진 url"),

                        fieldWithPath("videoUrl")
                            .type(JsonFieldType.STRING)
                            .description("비디오 url")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 아이템")
        void fail() throws Exception {
            doThrow(new ItemNotFoundException())
                .when(adminService)
                .updateItem(any(), any());

            mockMvc.perform(delete(ADMIN_ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ITEM_수정_요청()))
                )
                .andDo(print())
                .andDo(document("admin/updateItem/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("존재하지 않는 아이템 ID")
                    ),
                    requestFields(
                        fieldWithPath("enName")
                            .type(JsonFieldType.STRING)
                            .description("상품 영어 이름"),

                        fieldWithPath("koName")
                            .type(JsonFieldType.STRING)
                            .description("상품 한국 이름"),

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

                        fieldWithPath("images")
                            .type(JsonFieldType.ARRAY)
                            .description("여러 아이템 이미지"),

                        fieldWithPath("images[].path")
                            .type(JsonFieldType.STRING)
                            .description("아이템 이미지 주소"),

                        fieldWithPath("images[].url")
                            .type(JsonFieldType.STRING)
                            .description("아이템 이미지 url"),

                        fieldWithPath("images[].ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("아이템 이미지 순서"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionGroupName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 그룹 이름"),

                        fieldWithPath("itemOptionGroupInfoList[].ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 그룹 순서"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionInfoList[].itemOptionName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 이름"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionInfoList[].itemOptionPrice")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 가격"),

                        fieldWithPath("itemOptionGroupInfoList[].itemOptionInfoList[].ordering")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 순서"),

                        fieldWithPath("grayImage.path")
                            .type(JsonFieldType.STRING)
                            .description("흑백사진 주소"),

                        fieldWithPath("grayImage.url")
                            .type(JsonFieldType.STRING)
                            .description("흑백사진 url"),

                        fieldWithPath("videoUrl")
                            .type(JsonFieldType.STRING)
                            .description("비디오 url")
                    )
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("아이템 상태를 변경한다")
    class changeStatus {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            willDoNothing()
                .given(adminService)
                .changeItemStatus(any(), any());

            mockMvc.perform(put(ADMIN_ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ITEM_상태_변경_요청()))
                )
                .andDo(print())
                .andDo(document("admin/changeItemStatus",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("아이템 ID")
                    ),
                    requestFields(
                        fieldWithPath("itemStatus")
                            .type(JsonFieldType.STRING)
                            .description("아이템 상태")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 어드민이 아닌 유저")
        void fail() throws Exception {
            doThrow(new NotAdminRoleException())
                .when(adminService)
                .changeItemStatus(any(), any());

            mockMvc.perform(put(ADMIN_ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ITEM_상태_변경_요청()))
                )
                .andDo(print())
                .andDo(document("admin/changeItemStatus/fail/forbidden",
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
                .andExpect(status().isForbidden());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 아이템")
        void fail2() throws Exception {
            doThrow(new ItemNotFoundException())
                .when(adminService)
                .changeItemStatus(any(), any());

            mockMvc.perform(put(ADMIN_ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ITEM_상태_변경_요청()))
                )
                .andDo(print())
                .andDo(document("admin/changeItemStatus/fail/notFound",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                            headerWithName("Authorization")
                                .description("JWT Access Token")
                        ),
                        pathParameters(
                            parameterWithName("id")
                                .description("존재하지 않는 아이템 id")
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

    @Nested
    @DisplayName("아이템을 삭제한다")
    class delete {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            willDoNothing()
                .given(adminService)
                .deleteItem(any());

            mockMvc.perform(delete(ADMIN_ITEM_DEFAULT_URL + "/{name}", 아이템_영어_이름)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("admin/deleteItem",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("name")
                            .description("아이템 영어이름")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 아이템")
        void fail() throws Exception {
            doThrow(new ItemNotFoundException())
                .when(adminService)
                .deleteItem(any());

            mockMvc.perform(delete(ADMIN_ITEM_DEFAULT_URL + "/{name}", 아이템_영어_이름)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("admin/deleteItem/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("name")
                            .description("존재하지 않는 아이템 영어이름")
                    )
                ))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("모든 유저의 주문들을 조회한다")
    class getAllOrders {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(adminService.getAllOrder(any(), any(), any(), any()))
                .willReturn(ORDER_전체_조회_응답());

            mockMvc.perform(get(ADMIN_ORDER_DEFAULT_URL)
                        .queryParam("orderStatus", "ORDER")
                        .queryParam("deliveryTrackingNumber", "12345-6789")
                        .queryParam("customerName", "남세원")
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("admin/getAllOrder",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    queryParameters(
                        parameterWithName("orderStatus")
                            .description("주문 상태"),

                        parameterWithName("deliveryTrackingNumber")
                            .description("송장 번호"),

                        parameterWithName("customerName")
                            .description("주문자 이름")
                    ),
                    responseFields(ORDER_전체_주문_조회_응답_필드())
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("주문을 상세조회한다")
    class getOrder {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(adminService.getOrder(any()))
                .willReturn(ORDER_조회_응답());

            mockMvc.perform(get(ADMIN_ORDER_DEFAULT_URL + "/{id}", ORDER_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("admin/getOrder",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    pathParameters(
                        parameterWithName("id")
                            .description("주문 id")
                    ),
                    responseFields(
                        fieldWithPath("orderItems")
                            .type(JsonFieldType.ARRAY)
                            .description("주문한 상품들"),

                        fieldWithPath("orderItems[]imageUrl")
                            .type(JsonFieldType.STRING)
                            .description("주문한 상품 이미지 url"),

                        fieldWithPath("orderItems[]name")
                            .type(JsonFieldType.STRING)
                            .description("주문한 상품 이름"),

                        fieldWithPath("orderItems[]amount")
                            .type(JsonFieldType.NUMBER)
                            .description("주문한 상품 개수"),

                        fieldWithPath("orderItems[]price")
                            .type(JsonFieldType.NUMBER)
                            .description("주문한 상품 가격"),

                        fieldWithPath("receiverName")
                            .type(JsonFieldType.STRING)
                            .description("받는사람"),

                        fieldWithPath("receivedAddress")
                            .type(JsonFieldType.STRING)
                            .description("받는주소"),

                        fieldWithPath("orderStatus")
                            .type(JsonFieldType.STRING)
                            .description("주문상태"),

                        fieldWithPath("deliveryTrackingNumber")
                            .type(JsonFieldType.STRING)
                            .description("배송 송장번호")
                    )
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("배송 송장번호를 등록한다")
    class registerDeliveryTrackingNumber {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            willDoNothing()
                .given(adminService)
                .registerDeliveryTrackingNumber(any(), any());
            mockMvc.perform(post(ADMIN_ORDER_DEFAULT_URL + "/{id}", ORDER_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(송장번호_등록_요청())))
                .andDo(print())
                .andDo(document("admin/registerDeliveryTrackingNumber",
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
                        fieldWithPath("trackingNumber")
                            .type(JsonFieldType.STRING)
                            .description("송장 번호")
                    )
                ))
                .andExpect(status().isOk());
        }
    }
}

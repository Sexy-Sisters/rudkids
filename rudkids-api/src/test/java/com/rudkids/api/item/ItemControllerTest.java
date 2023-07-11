package com.rudkids.api.item;

import com.rudkids.api.common.ControllerTest;
import com.rudkids.core.item.exception.ItemNotFoundException;
import com.rudkids.core.product.exception.ProductNotFoundException;
import com.rudkids.core.user.exception.NotAdminOrPartnerRoleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.api.item.ItemFixturesAndDocs.*;
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
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ItemControllerTest extends ControllerTest {

    @Nested
    @DisplayName("아이템을 생성한다")
    class create {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(itemService.create(any(), any(), any()))
                .willReturn(아이템_영어_이름);

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

                        fieldWithPath("itemOptionGroupList[].itemOptionGroupName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 그룹 이름"),

                        fieldWithPath("itemOptionGroupList[].itemOptionList[].itemOptionName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 이름"),

                        fieldWithPath("itemOptionGroupList[].itemOptionList[].itemOptionPrice")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 가격"),

                        fieldWithPath("itemOptionGroupList[].itemOptionList[].itemOptionPrice")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 가격"),

                        fieldWithPath("videoImage")
                            .type(JsonFieldType.OBJECT)
                            .description("콘티영상 url"),

                        fieldWithPath("videoImage.path")
                            .type(JsonFieldType.STRING)
                            .description("콘티영상 이미지 path"),

                        fieldWithPath("videoImage.url")
                            .type(JsonFieldType.STRING)
                            .description("콘티영상 이미지 url"),

                        fieldWithPath("videoUrl")
                            .type(JsonFieldType.STRING)
                            .description("콘티영상 url")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 어드민이 아닌 유저")
        void fail() throws Exception {
            doThrow(new NotAdminOrPartnerRoleException())
                .when(itemService)
                .create(any(), any(), any());

            mockMvc.perform(post(ITEM_DEFAULT_URL + "/{productId}", 프로덕트_아이디)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ITEM_등록_요청()))
                ).andDo(print())
                .andDo(document("item/create/fail/forbidden",
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

                        fieldWithPath("itemOptionGroupList[].itemOptionGroupName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 그룹 이름"),

                        fieldWithPath("itemOptionGroupList[].itemOptionList[].itemOptionName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 이름"),

                        fieldWithPath("itemOptionGroupList[].itemOptionList[].itemOptionPrice")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 가격"),

                        fieldWithPath("videoImage")
                            .type(JsonFieldType.OBJECT)
                            .description("콘티영상 url"),

                        fieldWithPath("videoImage.path")
                            .type(JsonFieldType.STRING)
                            .description("콘티영상 이미지 path"),

                        fieldWithPath("videoImage.url")
                            .type(JsonFieldType.STRING)
                            .description("콘티영상 이미지 url"),

                        fieldWithPath("videoUrl")
                            .type(JsonFieldType.STRING)
                            .description("콘티영상 url")
                    ),
                    responseFields(
                        fieldWithPath("message")
                            .type(JsonFieldType.STRING)
                            .description("에러 메시지")
                    )
                ))
                .andExpect(status().isForbidden());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 프로덕트")
        void fail2() throws Exception {
            doThrow(new ProductNotFoundException())
                .when(itemService)
                .create(any(), any(), any());

            mockMvc.perform(post(ITEM_DEFAULT_URL + "/{productId}", 프로덕트_아이디)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ITEM_등록_요청()))
                ).andDo(print())
                .andDo(document("item/create/fail/notFound",
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

                        fieldWithPath("itemOptionGroupList[].itemOptionGroupName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 그룹 이름"),

                        fieldWithPath("itemOptionGroupList[].itemOptionList[].itemOptionName")
                            .type(JsonFieldType.STRING)
                            .description("옵션 이름"),

                        fieldWithPath("itemOptionGroupList[].itemOptionList[].itemOptionPrice")
                            .type(JsonFieldType.NUMBER)
                            .description("옵션 가격"),

                        fieldWithPath("videoImage")
                            .type(JsonFieldType.OBJECT)
                            .description("콘티영상 url"),

                        fieldWithPath("videoImage.path")
                            .type(JsonFieldType.STRING)
                            .description("콘티영상 이미지 path"),

                        fieldWithPath("videoImage.url")
                            .type(JsonFieldType.STRING)
                            .description("콘티영상 이미지 url"),

                        fieldWithPath("videoUrl")
                            .type(JsonFieldType.STRING)
                            .description("콘티영상 url")
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

                        fieldWithPath("itemStatus")
                            .type(JsonFieldType.STRING)
                            .description("상태"),

                        fieldWithPath("itemOptionGroupInfoList")
                            .type(JsonFieldType.NULL)
                            .description("옵션 그룹 응답 리스트 (테스트 코드 수정 필요"),

                        fieldWithPath("videoImage")
                            .type(JsonFieldType.OBJECT)
                            .description("콘티영상 썸네일"),

                        fieldWithPath("videoImage.path")
                            .type(JsonFieldType.STRING)
                            .description("콘티영상 썸네일 path"),

                        fieldWithPath("videoImage.url")
                            .type(JsonFieldType.STRING)
                            .description("콘티영상 썸네일 url"),

                        fieldWithPath("videoUrl")
                            .type(JsonFieldType.STRING)
                            .description("콘티영상 url")
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

    @Nested
    @DisplayName("아이템 비디오 썸네일들을 조회한다")
    class getItemVideoImages {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(itemService.getItemVideoImages(any()))
                .willReturn(ITEM_영상_이미지_응답());

            mockMvc.perform(get(ITEM_DEFAULT_URL + "/video-image"))
                .andDo(print())
                .andDo(document("item/getItemVideoImages",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    responseFields(ITEM_영상_이미지_응답_필드())
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("아이템 이름으로 비디오를 조회한다")
    class getItemVideo {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(itemService.getItemVideo(any()))
                .willReturn(ITEM_영상_응답());

            mockMvc.perform(get(ITEM_DEFAULT_URL + "/video?name={name}", 아이템_영어_이름))
                .andDo(print())
                .andDo(document("item/getItemVideo",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    queryParameters(
                        parameterWithName("name")
                            .description("아이템 영어이름")
                    ),
                    responseFields(
                        fieldWithPath("name")
                            .type(JsonFieldType.STRING)
                            .description("상품 영어 이름"),

                        fieldWithPath("videoUrl")
                            .type(JsonFieldType.STRING)
                            .description("상품 영상 url")
                    )
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("아이템정보를 수정한다")
    class update {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            willDoNothing()
                .given(itemService)
                .update(any(), any(), any());

            mockMvc.perform(delete(ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ITEM_수정_요청()))
                )
                .andDo(print())
                .andDo(document("item/update",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id").description("아이템 ID")
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
                            .description("아이템 이미지 url")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 아이템")
        void fail() throws Exception {
            doThrow(new ItemNotFoundException())
                .when(itemService)
                .update(any(), any(), any());

            mockMvc.perform(delete(ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ITEM_수정_요청()))
                )
                .andDo(print())
                .andDo(document("item/update/fail/notFound",
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
                            .description("아이템 이미지 url")
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
            given(itemService.changeStatus(any(), any(), any()))
                .willReturn(아이템_상태);

            mockMvc.perform(put(ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ITEM_상태_변경_요청()))
                )
                .andDo(print())
                .andDo(document("item/changeStatus",
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
            doThrow(new NotAdminOrPartnerRoleException())
                .when(itemService)
                .changeStatus(any(), any(), any());

            mockMvc.perform(put(ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ITEM_상태_변경_요청()))
                )
                .andDo(print())
                .andDo(document("item/changeStatus/fail/forbidden",
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
                .when(itemService)
                .changeStatus(any(), any(), any());

            mockMvc.perform(put(ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ITEM_상태_변경_요청()))
                )
                .andDo(print())
                .andDo(document("item/changeStatus/fail/notFound",
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
                .given(itemService)
                .delete(any(), any());

            mockMvc.perform(delete(ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("item/delete",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id").description("아이템 ID")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 아이템")
        void fail() throws Exception {
            doThrow(new ItemNotFoundException())
                .when(itemService)
                .delete(any(), any());

            mockMvc.perform(delete(ITEM_DEFAULT_URL + "/{id}", 아이템_아이디)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("item/delete/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("존재하지 않는 아이템 ID")
                    )
                ))
                .andExpect(status().isNotFound());
        }
    }
}
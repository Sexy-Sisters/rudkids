package com.rudkids.api.delivery;

import com.rudkids.api.common.ControllerTest;
import com.rudkids.core.community.exception.CommunityNotFoundException;
import com.rudkids.core.delivery.exception.DeliveryNotFoundException;
import com.rudkids.core.user.exception.DifferentUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.api.community.CommunityFixturesAndDocs.*;
import static com.rudkids.api.delivery.DeliveryFixturesAndDocs.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeliveryControllerTest extends ControllerTest {

    @Nested
    @DisplayName("배송지를 등록한다")
    class create {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(deliveryService.create(any(), any()))
                .willReturn(DELIVERY_ID);

            mockMvc.perform(post(DELIVERY_DEFAULT_URL)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(DELIVERY_등록_요청())))
                .andDo(print())
                .andDo(document("delivery/create",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    requestFields(
                        fieldWithPath("receiverName")
                            .type(JsonFieldType.STRING)
                            .description("받는사람 이름"),

                        fieldWithPath("receiverPhone")
                            .type(JsonFieldType.STRING)
                            .description("받는사람 폰번호"),

                        fieldWithPath("zipCode")
                            .type(JsonFieldType.STRING)
                            .description("우편번호"),

                        fieldWithPath("address")
                            .type(JsonFieldType.STRING)
                            .description("주소"),

                        fieldWithPath("extraAddress")
                            .type(JsonFieldType.STRING)
                            .description("상세주소"),

                        fieldWithPath("message")
                            .type(JsonFieldType.STRING)
                            .description("메세지"),

                        fieldWithPath("isBasic")
                            .type(JsonFieldType.BOOLEAN)
                            .description("기본배송지 여부")
                    )
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("배송지 리스트를 조회한다")
    class getAll {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(deliveryService.getAll(any()))
                .willReturn(DELIVERY_리스트_응답());

            mockMvc.perform(get(DELIVERY_DEFAULT_URL)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("delivery/getAll",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    responseFields(
                        fieldWithPath("[]deliveryId")
                            .type(JsonFieldType.STRING)
                            .description("배송지 id"),

                        fieldWithPath("[]receiverName")
                            .type(JsonFieldType.STRING)
                            .description("받는사람 이름"),

                        fieldWithPath("[]receiverPhone")
                            .type(JsonFieldType.STRING)
                            .description("받는사람 폰번호"),

                        fieldWithPath("[]zipCode")
                            .type(JsonFieldType.STRING)
                            .description("우편번호"),

                        fieldWithPath("[]address")
                            .type(JsonFieldType.STRING)
                            .description("주소"),

                        fieldWithPath("[]extraAddress")
                            .type(JsonFieldType.STRING)
                            .description("상세주소"),

                        fieldWithPath("[]message")
                            .type(JsonFieldType.STRING)
                            .description("메세지"),

                        fieldWithPath("[]isBasic")
                            .type(JsonFieldType.BOOLEAN)
                            .description("기본배송지 여부")
                    )
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("배송지 정보를 상세조회한다")
    class get {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(deliveryService.get(any(), any()))
                .willReturn(DELIVERY_상세조회_응답());

            mockMvc.perform(get(DELIVERY_DEFAULT_URL + "/{id}", DELIVERY_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("delivery/get",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    pathParameters(
                        parameterWithName("id")
                            .description("배송지 id")
                    ),
                    responseFields(
                        fieldWithPath("deliveryId")
                            .type(JsonFieldType.STRING)
                            .description("배송지 id"),

                        fieldWithPath("receiverName")
                            .type(JsonFieldType.STRING)
                            .description("받는사람 이름"),

                        fieldWithPath("receiverPhone")
                            .type(JsonFieldType.STRING)
                            .description("받는사람 폰번호"),

                        fieldWithPath("zipCode")
                            .type(JsonFieldType.STRING)
                            .description("우편번호"),

                        fieldWithPath("address")
                            .type(JsonFieldType.STRING)
                            .description("주소"),

                        fieldWithPath("extraAddress")
                            .type(JsonFieldType.STRING)
                            .description("상세주소"),

                        fieldWithPath("message")
                            .type(JsonFieldType.STRING)
                            .description("메세지"),

                        fieldWithPath("isBasic")
                            .type(JsonFieldType.BOOLEAN)
                            .description("기본배송지 여부")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 배송지")
        void fail() throws Exception {
            doThrow(new DeliveryNotFoundException())
                .when(deliveryService)
                .get(any(), any());

            mockMvc.perform(get(DELIVERY_DEFAULT_URL + "/{id}", DELIVERY_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("delivery/get/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    pathParameters(
                        parameterWithName("id")
                            .description("배송지 id")
                    )
                ))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("배송지를 기본배송지 상태로 변경한다")
    class changeBasicStatus {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            willDoNothing()
                .given(deliveryService)
                .changeStatus(any(), any());

            mockMvc.perform(patch(DELIVERY_DEFAULT_URL + "/{id}", DELIVERY_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("delivery/changeBasicStatus",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    pathParameters(
                        parameterWithName("id")
                            .description("배송지 id")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 다른유저의 배송지")
        void fail() throws Exception {
            doThrow(new DifferentUserException())
                .when(deliveryService)
                .changeStatus(any(), any());

            mockMvc.perform(patch(DELIVERY_DEFAULT_URL + "/{id}", DELIVERY_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("delivery/changeBasicStatus/fail/forbidden",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    pathParameters(
                        parameterWithName("id")
                            .description("배송지 id")
                    )
                ))
                .andExpect(status().isForbidden());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 배송지")
        void fail2() throws Exception {
            doThrow(new DeliveryNotFoundException())
                .when(deliveryService)
                .changeStatus(any(), any());

            mockMvc.perform(patch(DELIVERY_DEFAULT_URL + "/{id}", DELIVERY_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("delivery/changeBasicStatus/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    pathParameters(
                        parameterWithName("id")
                            .description("존재하지 않는 배송지 id")
                    )
                ))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("배송지 정보를 수정한다")
    class update {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            willDoNothing()
                .given(deliveryService)
                .update(any(), any(), any());

            mockMvc.perform(put(DELIVERY_DEFAULT_URL + "/{id}", DELIVERY_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(DELIVERY_수정_요청())))
                .andDo(print())
                .andDo(document("delivery/update",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    pathParameters(
                        parameterWithName("id")
                            .description("배송지 id")
                    ),
                    requestFields(
                        fieldWithPath("receiverName")
                            .type(JsonFieldType.STRING)
                            .description("받는사람 이름"),

                        fieldWithPath("receiverPhone")
                            .type(JsonFieldType.STRING)
                            .description("받는사람 폰번호"),

                        fieldWithPath("zipCode")
                            .type(JsonFieldType.STRING)
                            .description("우편번호"),

                        fieldWithPath("address")
                            .type(JsonFieldType.STRING)
                            .description("주소"),

                        fieldWithPath("extraAddress")
                            .type(JsonFieldType.STRING)
                            .description("상세주소"),

                        fieldWithPath("message")
                            .type(JsonFieldType.STRING)
                            .description("메세지")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 다른유저의 배송지")
        void fail() throws Exception {
            doThrow(new DifferentUserException())
                .when(deliveryService)
                .update(any(), any(), any());

            mockMvc.perform(put(DELIVERY_DEFAULT_URL + "/{id}", DELIVERY_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(DELIVERY_수정_요청())))
                .andDo(print())
                .andDo(document("delivery/update/fail/forbidden",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    pathParameters(
                        parameterWithName("id")
                            .description("배송지 id")
                    ),
                    requestFields(
                        fieldWithPath("receiverName")
                            .type(JsonFieldType.STRING)
                            .description("받는사람 이름"),

                        fieldWithPath("receiverPhone")
                            .type(JsonFieldType.STRING)
                            .description("받는사람 폰번호"),

                        fieldWithPath("zipCode")
                            .type(JsonFieldType.STRING)
                            .description("우편번호"),

                        fieldWithPath("address")
                            .type(JsonFieldType.STRING)
                            .description("주소"),

                        fieldWithPath("extraAddress")
                            .type(JsonFieldType.STRING)
                            .description("상세주소"),

                        fieldWithPath("message")
                            .type(JsonFieldType.STRING)
                            .description("메세지")
                    )
                ))
                .andExpect(status().isForbidden());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 배송지")
        void fail2() throws Exception {
            doThrow(new DeliveryNotFoundException())
                .when(deliveryService)
                .update(any(), any(), any());

            mockMvc.perform(put(DELIVERY_DEFAULT_URL + "/{id}", DELIVERY_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(DELIVERY_수정_요청())))
                .andDo(print())
                .andDo(document("delivery/update/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    pathParameters(
                        parameterWithName("id")
                            .description("배송지 id")
                    ),
                    requestFields(
                        fieldWithPath("receiverName")
                            .type(JsonFieldType.STRING)
                            .description("받는사람 이름"),

                        fieldWithPath("receiverPhone")
                            .type(JsonFieldType.STRING)
                            .description("받는사람 폰번호"),

                        fieldWithPath("zipCode")
                            .type(JsonFieldType.STRING)
                            .description("우편번호"),

                        fieldWithPath("address")
                            .type(JsonFieldType.STRING)
                            .description("주소"),

                        fieldWithPath("extraAddress")
                            .type(JsonFieldType.STRING)
                            .description("상세주소"),

                        fieldWithPath("message")
                            .type(JsonFieldType.STRING)
                            .description("메세지")
                    )
                ))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("배송지를 삭제한다")
    class delete {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            willDoNothing()
                .given(deliveryService)
                .delete(any(), any());

            mockMvc.perform(delete(DELIVERY_DEFAULT_URL + "/{id}", DELIVERY_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("delivery/delete",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    pathParameters(
                        parameterWithName("id")
                            .description("배송지 id")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 다른유저의 배송지")
        void fail() throws Exception {
            doThrow(new DifferentUserException())
                .when(deliveryService)
                .delete(any(), any());

            mockMvc.perform(delete(DELIVERY_DEFAULT_URL + "/{id}", DELIVERY_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("delivery/delete/fail/forbidden",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    pathParameters(
                        parameterWithName("id")
                            .description("배송지 id")
                    )
                ))
                .andExpect(status().isForbidden());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 배송지")
        void fail2() throws Exception {
            doThrow(new DeliveryNotFoundException())
                .when(deliveryService)
                .delete(any(), any());

            mockMvc.perform(delete(DELIVERY_DEFAULT_URL + "/{id}", DELIVERY_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("delivery/delete/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    pathParameters(
                        parameterWithName("id")
                            .description("존재하지 않는 배송지 id")
                    )
                ))
                .andExpect(status().isNotFound());
        }
    }
}

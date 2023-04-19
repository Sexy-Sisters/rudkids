package com.rudkids.rudkids.interfaces.magazine;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.magazine.exception.MagazineNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.auth.AuthControllerFixtures.AUTHORIZATION_HEADER_NAME;
import static com.rudkids.rudkids.common.fixtures.auth.AuthControllerFixtures.AUTHORIZATION_HEADER_VALUE;
import static com.rudkids.rudkids.common.fixtures.magazine.MagazineControllerFixtures.*;
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

class MagazineControllerTest extends ControllerTest {

    @DisplayName("메거진을 작성한다.")
    @Test
    void 매거진을_작성한다() throws Exception {
        willDoNothing()
            .given(magazineService)
            .create(any(), any());

        mockMvc.perform(post(MAGAZINE_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(MAGAZINE_작성_요청())))
            .andDo(print())
            .andDo(document("magazine/create",
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

                    fieldWithPath("content")
                        .type(JsonFieldType.STRING)
                        .description("내용")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("매거진을 수정한다.")
    @Test
    void 매거진을_수정한다() throws Exception {
        willDoNothing()
            .given(magazineService)
            .update(any(), any(), any());

        mockMvc.perform(put(MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID)
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
                        .description("새로운 내용")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("존재하지 않는 매거진을 수정할 경우 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_매거진을_수정할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new MagazineNotFoundException())
            .when(magazineService)
            .update(any(), any(), any());

        mockMvc.perform(put(MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID)
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

    @DisplayName("매거진을 삭제한다.")
    @Test
    void 매거진을_삭제한다() throws Exception {
        willDoNothing()
            .given(magazineService)
            .delete(any(), any());

        mockMvc.perform(delete(MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID)
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

    @DisplayName("존재하지 않는 매거진을 삭제할 경우 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_매거진을_삭제할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new MagazineNotFoundException())
            .when(magazineService)
            .delete(any(), any());

        mockMvc.perform(delete(MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID)
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

    @DisplayName("매거진을 전체조회한다.")
    @Test
    void 매거진을_전체조회한다() throws Exception {
        given(magazineService.findAll()).willReturn(MAGAZINE_전체조회_응답());

        mockMvc.perform(get(MAGAZINE_DEFAULT_URL))
            .andDo(print())
            .andDo(document("magazine/findAll",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("[].title")
                        .type(JsonFieldType.STRING)
                        .description("매거진 제목"),

                    fieldWithPath("[].writer")
                        .type(JsonFieldType.STRING)
                        .description("매거진 작성자")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("매거진을 상세조회한다.")
    @Test
    void 매거진을_상세조회한다() throws Exception {
        given(magazineService.find(any())).willReturn(MAGAZINE_상세조회_응답());

        mockMvc.perform(get(MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID))
            .andDo(print())
            .andDo(document("magazine/find",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("id")
                        .description("매거진 id")
                ),
                responseFields(
                    fieldWithPath("title")
                        .type(JsonFieldType.STRING)
                        .description("매거진 제목"),

                    fieldWithPath("writer")
                        .type(JsonFieldType.STRING)
                        .description("매거진 작성자"),

                    fieldWithPath("content")
                        .type(JsonFieldType.STRING)
                        .description("매거진 내용")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("존재하지 않는 매거진을 상세조회할 경우 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_매거진을_상세조회할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new MagazineNotFoundException())
            .when(magazineService)
            .find(any());

        mockMvc.perform(get(MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID))
            .andDo(print())
            .andDo(document("magazine/find/failByNotFoundError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("id")
                        .description("매거진 id")
                )
            ))
            .andExpect(status().isNotFound());
    }
}
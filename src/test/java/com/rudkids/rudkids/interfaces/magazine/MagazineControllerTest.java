package com.rudkids.rudkids.interfaces.magazine;

import com.rudkids.rudkids.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.magazine.MagazineControllerFixtures.*;
import static com.rudkids.rudkids.common.fixtures.user.UserControllerFixtures.AUTHORIZATION_HEADER_NAME;
import static com.rudkids.rudkids.common.fixtures.user.UserControllerFixtures.AUTHORIZATION_HEADER_VALUE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MagazineControllerTest extends ControllerTest {

    @DisplayName("장바구니에 아이템을 추가한다.")
    @Test
    void 장바구니에_아이템을_추가한다() throws Exception {
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
}
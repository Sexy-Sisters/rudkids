package com.rudkids.rudkids.interfaces.user;

import com.rudkids.rudkids.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.user.UserControllerFixtures.USER_DEFAULT_URL;
import static com.rudkids.rudkids.common.fixtures.user.UserControllerFixtures.USER_수정_요청;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends ControllerTest {

    @DisplayName("[유저-수정]")
    @Test
    void 유저_정보를_수정한다() throws Exception {
        willDoNothing()
            .given(userService)
            .update(any(), any());

        mockMvc.perform(put(USER_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(USER_수정_요청())))
            .andDo(print())
            .andDo(document("user/update",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                requestFields(
                    fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .description("유저 이름"),

                    fieldWithPath("phoneNumber")
                        .type(JsonFieldType.STRING)
                        .description("유저 폰번호"),

                    fieldWithPath("profileImagePath")
                        .type(JsonFieldType.STRING)
                        .description("유저 이미지 주소"),

                    fieldWithPath("profileImageUrl")
                        .type(JsonFieldType.STRING)
                        .description("유저 이미지 url")
                )
            ))
            .andExpect(status().isOk());
    }
}
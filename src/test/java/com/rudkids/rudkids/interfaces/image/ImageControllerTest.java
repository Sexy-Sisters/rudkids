package com.rudkids.rudkids.interfaces.image;

import com.rudkids.rudkids.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.image.ImageControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ImageControllerTest extends ControllerTest {

    @DisplayName("[이미지-업로드]")
    @Test
    void 하나의_파일을_업로드_한다() throws Exception {
        given(imageService.upload(any()))
            .willReturn(IMAGE_응답());

        mockMvc.perform(multipart(IMAGE_DEFAULT_URL)
                .file(IMAGE_FILE())
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
            .andDo(print())
            .andDo(document("image/upload/one",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                responseFields(
                    fieldWithPath("path")
                        .type(JsonFieldType.STRING)
                        .description("이미지 경로"),

                    fieldWithPath("url")
                        .type(JsonFieldType.STRING)
                        .description("이미지 url")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[여러-이미지-업로드]")
    @Test
    void 여러개의_파일을_업로드_한다() throws Exception {
        given(imageService.uploads(any()))
            .willReturn(IMAGE_여러개_응답());

        mockMvc.perform(multipart(IMAGE_DEFAULT_URL + "/list")
                .file(IMAGE_FILES().get(0))
                .file(IMAGE_FILES().get(1))
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
            .andDo(print())
            .andDo(document("image/upload/list",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                responseFields(
                    fieldWithPath("images[].path")
                        .type(JsonFieldType.STRING)
                        .description("이미지 경로"),

                    fieldWithPath("images[].url")
                        .type(JsonFieldType.STRING)
                        .description("이미지 url")
                )
            ))
            .andExpect(status().isOk());
    }
}
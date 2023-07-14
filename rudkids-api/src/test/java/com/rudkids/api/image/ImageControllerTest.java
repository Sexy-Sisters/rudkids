package com.rudkids.api.image;

import com.rudkids.api.common.ControllerTest;
import com.rudkids.core.image.exception.FileNameEmptyException;
import com.rudkids.core.image.exception.FileUploadFailException;
import com.rudkids.core.image.exception.UnsupportedFileExtensionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.api.common.fixtures.image.ImageFixturesAndDocs.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
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

    @Nested
    @DisplayName("파일을 업로드한다")
    class upload {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(imageService.upload(any()))
                .willReturn(IMAGE_응답());

            mockMvc.perform(multipart(IMAGE_DEFAULT_URL)
                    .file(IMAGE_FILE())
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("image/upload",
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

        @Test
        @DisplayName("실패: 비어있는 파일이름")
        void fail() throws Exception {
            doThrow(new FileNameEmptyException())
                .when(imageService)
                .upload(any());

            mockMvc.perform(multipart(IMAGE_DEFAULT_URL)
                    .file(IMAGE_FILE())
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("image/upload/fail/badRequest/empty",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    )
                ))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패: 지원하지 않는 확장자")
        void fail2() throws Exception {
            doThrow(new UnsupportedFileExtensionException())
                .when(imageService)
                .upload(any());

            mockMvc.perform(multipart(IMAGE_DEFAULT_URL)
                    .file(IMAGE_FILE())
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("image/upload/fail/badRequest/extension",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    )
                ))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패: 외부 API 통신")
        void fail3() throws Exception {
            doThrow(new FileUploadFailException())
                .when(imageService)
                .upload(any());

            mockMvc.perform(multipart(IMAGE_DEFAULT_URL)
                    .file(IMAGE_FILE())
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("image/upload/fail/internal",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    )
                ))
                .andExpect(status().isInternalServerError());
        }
    }
}
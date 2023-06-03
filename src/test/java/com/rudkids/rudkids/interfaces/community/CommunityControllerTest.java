package com.rudkids.rudkids.interfaces.community;

import com.rudkids.rudkids.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.magazine.MagazineControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CommunityControllerTest extends ControllerTest {

//    @DisplayName("[매거진-전체조회]")
//    @Test
//    void 매거진을_전체조회한다() throws Exception {
//        given(communityService.findAll()).willReturn(MAGAZINE_전체조회_응답());
//
//        mockMvc.perform(get(MAGAZINE_DEFAULT_URL))
//            .andDo(print())
//            .andDo(document("magazine/findAll",
//                preprocessRequest(prettyPrint()),
//                preprocessResponse(prettyPrint()),
//                responseFields(
//                    fieldWithPath("[].title")
//                        .type(JsonFieldType.STRING)
//                        .description("매거진 제목"),
//
//                    fieldWithPath("[].writer")
//                        .type(JsonFieldType.STRING)
//                        .description("매거진 작성자")
//                )
//            ))
//            .andExpect(status().isOk());
//    }

//    @DisplayName("[매거진-상세조회]")
//    @Test
//    void 매거진을_상세조회한다() throws Exception {
//        given(communityService.find(any())).willReturn(MAGAZINE_상세조회_응답());
//
//        mockMvc.perform(get(MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID))
//            .andDo(print())
//            .andDo(document("magazine/find",
//                preprocessRequest(prettyPrint()),
//                preprocessResponse(prettyPrint()),
//                pathParameters(
//                    parameterWithName("id")
//                        .description("매거진 id")
//                ),
//                responseFields(
//                    fieldWithPath("title")
//                        .type(JsonFieldType.STRING)
//                        .description("매거진 제목"),
//
//                    fieldWithPath("writer")
//                        .type(JsonFieldType.STRING)
//                        .description("매거진 작성자"),
//
//                    fieldWithPath("content")
//                        .type(JsonFieldType.STRING)
//                        .description("매거진 내용")
//                )
//            ))
//            .andExpect(status().isOk());
//    }
}
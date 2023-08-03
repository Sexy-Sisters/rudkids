package com.rudkids.api.product;

import com.rudkids.api.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.api.common.fixtures.product.MysteryProductFixturesAndDocs.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MysteryProductControllerTest extends ControllerTest {

    @Nested
    @DisplayName("미스테리 프로덕트를 조회한다")
    class get {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(mysteryProductService.get())
                .willReturn(미스테리_프로덕트_응답());

            mockMvc.perform(get(MYSTERY_PRODUCT_DEFAULT_URL))
                .andDo(print())
                .andDo(document("product/mystery/get",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    responseFields(
                        fieldWithPath("productId")
                            .type(JsonFieldType.STRING)
                            .description("id"),

                        fieldWithPath("title")
                            .type(JsonFieldType.STRING)
                            .description("제목"),

                        fieldWithPath("frontImageUrl")
                            .type(JsonFieldType.STRING)
                            .description("앞 이미지 url"),

                        fieldWithPath("backImageUrl")
                            .type(JsonFieldType.STRING)
                            .description("뒤 이미지 url")
                    )))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("미스테리 프로덕트를 상세조회한다")
    class getDetail {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(mysteryProductService.getDetail(any(), any(), any()))
                .willReturn(미스테리_프로덕트_상세_응답());

            mockMvc.perform(get(MYSTERY_PRODUCT_DEFAULT_URL + "/detail/{id}", 미스테리_프로덕트_아이디)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("product/mystery/getDetail",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("미스테리 프로덕트 id")
                    ),
                    responseFields(
                        fieldWithPath("title")
                            .type(JsonFieldType.STRING)
                            .description("제목"),

                        fieldWithPath("bio")
                            .type(JsonFieldType.STRING)
                            .description("소개글"),

                        fieldWithPath("frontImage")
                            .type(JsonFieldType.OBJECT)
                            .description("프로덕트 앞 이미지"),

                        fieldWithPath("frontImage.path")
                            .type(JsonFieldType.STRING)
                            .description("프로덕트 앞 이미지 path"),

                        fieldWithPath("frontImage.url")
                            .type(JsonFieldType.STRING)
                            .description("프로덕트 앞 이미지 url"),

                        fieldWithPath("backImage")
                            .type(JsonFieldType.OBJECT)
                            .description("프로덕트 뒤 이미지"),

                        fieldWithPath("backImage.path")
                            .type(JsonFieldType.STRING)
                            .description("프로덕트 뒤 이미지 path"),

                        fieldWithPath("backImage.url")
                            .type(JsonFieldType.STRING)
                            .description("프로덕트 뒤 이미지 url"),

                        fieldWithPath("bannerImage.path")
                            .type(JsonFieldType.STRING)
                            .description("배너 이미지 주소"),

                        fieldWithPath("bannerImage.url")
                            .type(JsonFieldType.STRING)
                            .description("배너 이미지 url"),

                        fieldWithPath("items.content")
                            .type(JsonFieldType.ARRAY)
                            .description("아이템들"),

                        fieldWithPath("items.content[].itemId")
                            .type(JsonFieldType.STRING)
                            .description("아이템 id"),

                        fieldWithPath("items.content[].name")
                            .type(JsonFieldType.STRING)
                            .description("아이템 이름"),

                        fieldWithPath("items.content[].price")
                            .type(JsonFieldType.NUMBER)
                            .description("아이템 가격"),

                        fieldWithPath("items.content[].imageUrl")
                            .type(JsonFieldType.STRING)
                            .description("여러 이미지 url"),

                        fieldWithPath("items.content[].itemStatus")
                            .type(JsonFieldType.STRING)
                            .description("아이템 상태"),

                        fieldWithPath("items.pageable")
                            .type(JsonFieldType.STRING)
                            .description("페이징 응답"),

                        fieldWithPath("items.last")
                            .type(JsonFieldType.BOOLEAN)
                            .description("마지막 페이지 인지 아닌지 여부"),

                        fieldWithPath("items.totalPages")
                            .type(JsonFieldType.NUMBER)
                            .description("총 페이지 수"),

                        fieldWithPath("items.totalElements")
                            .type(JsonFieldType.NUMBER)
                            .description("프로덕트 전체 데이터 갯수"),

                        fieldWithPath("items.first")
                            .type(JsonFieldType.BOOLEAN)
                            .description("첫번째 페이지 인지 여부"),

                        fieldWithPath("items.size")
                            .type(JsonFieldType.NUMBER)
                            .description("페이지 당 나타낼 수 있는 데이터의 갯수"),

                        fieldWithPath("items.number")
                            .type(JsonFieldType.NUMBER)
                            .description("현재 페이지의 번호"),

                        fieldWithPath("items.sort")
                            .type(JsonFieldType.OBJECT)
                            .description("정렬 값들"),

                        fieldWithPath("items.sort.empty")
                            .type(JsonFieldType.BOOLEAN)
                            .description("정렬 값이 비어있는지 여부"),

                        fieldWithPath("items.sort.sorted")
                            .type(JsonFieldType.BOOLEAN)
                            .description("정렬 했는지 여부"),

                        fieldWithPath("items.sort.unsorted")
                            .type(JsonFieldType.BOOLEAN)
                            .description("정렬 하지 않았는지 여부"),

                        fieldWithPath("items.numberOfElements")
                            .type(JsonFieldType.NUMBER)
                            .description("실제 데이터의 갯수"),

                        fieldWithPath("items.empty")
                            .type(JsonFieldType.BOOLEAN)
                            .description("리스트가 비어있는지 여부"),

                        fieldWithPath("mobileImage.path")
                            .type(JsonFieldType.STRING)
                            .description("프로덕트 모바일 이미지 path"),

                        fieldWithPath("mobileImage.url")
                            .type(JsonFieldType.STRING)
                            .description("프로덕트 모바일 이미지 url")
                    )))
                .andExpect(status().isOk());
        }
    }
}

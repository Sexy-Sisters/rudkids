package com.rudkids.rudkids.interfaces.product;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import com.rudkids.rudkids.domain.product.exception.ProductNotFoundException;
import com.rudkids.rudkids.domain.user.exception.NotAdminRoleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.rudkids.rudkids.common.fixtures.product.ProductControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest extends ControllerTest {

    @DisplayName("프로덕트를 등록한다.")
    @Test
    void 프로덕트를_등록한다() throws Exception {
        willDoNothing()
            .given(productService)
            .create(any(), any());

        mockMvc.perform(post("/api/v1/product")
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_등록_요청()))
            ).andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("관리자가 아닌 유저가 프로덕트를 등록하면 403을 반환한다.")
    @Test
    void 관리자가_아닌_유저가_프로덕트를_등록하면_403을_반환한다() throws Exception {
        doThrow(new NotAdminRoleException())
            .when(productService)
            .create(any(), any());

        mockMvc.perform(post("/api/v1/product")
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_등록_요청()))
            ).andDo(print())
            .andExpect(status().isForbidden());
    }

    @DisplayName("프로덕트 리스트를 조회한다.")
    @Test
    void 프로덕트_리스트를_조회한다() throws Exception {
        given(productService.findAll())
            .willReturn(PRODUCT_리스트_조회_응답());

        mockMvc.perform(get("/api/v1/product"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("프로덕트 세부사항을 조회한다.")
    @Test
    void 프로덕트_세부사항을_조회한다() throws Exception {
        given(productService.find(any()))
            .willReturn(PRODUCT_상세_조회_응답());

        mockMvc.perform(get(PRODUCT_DEFAULT_URL + "/{productId}", 프로덕트_아이디))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("존재하지 않는 프로덕트의 세부사항을 조회할 경우 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_프로덕트의_세부사항을_조회할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new ProductNotFoundException())
            .when(productService)
            .find(any());

        mockMvc.perform(get(PRODUCT_DEFAULT_URL + "/{productId}", 프로덕트_아이디))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @DisplayName("프로덕트를 연다.")
    @Test
    void 프로덕트를_연다() throws Exception {
        given(productService.openProduct(any(), any()))
            .willReturn(ProductStatus.OPEN);

        mockMvc.perform(put("/api/v1/product/{id}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
            )
            .andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("존재하지 않는 프로덕트를 오픈할 경우 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_프로덕트를_오픈할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new ProductNotFoundException())
            .when(productService)
            .openProduct(any(), any());


        mockMvc.perform(put("/api/v1/product/{id}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
            )
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @DisplayName("프로덕트를 닫는다.")
    @Test
    void 프로덕트를_닫는다() throws Exception {
        given(productService.closeProduct(any(), any()))
            .willReturn(ProductStatus.CLOSED);

        mockMvc.perform(delete("/api/v1/product/{id}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
            )
            .andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("존재하지 않는 프로덕트를 닫을 경우 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_프로덕트를_닫을_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new ProductNotFoundException())
            .when(productService)
            .closeProduct(any(), any());


        mockMvc.perform(delete("/api/v1/product/{id}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
            )
            .andDo(print())
            .andExpect(status().isNotFound());
    }
}
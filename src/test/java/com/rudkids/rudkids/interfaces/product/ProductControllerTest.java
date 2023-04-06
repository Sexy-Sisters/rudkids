package com.rudkids.rudkids.interfaces.product;

import com.rudkids.rudkids.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.rudkids.rudkids.common.fixtures.product.ProductControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProductControllerTest extends ControllerTest {

    @DisplayName("프로덕트를 등록한다.")
    @Test
    void 프로덕트를_등록한다() throws Exception {
        willDoNothing()
            .given(productService)
            .registerProduct(any());

        mockMvc.perform(post("/api/v1/product")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_등록_요청()))
            ).andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("프로덕트 리스트를 조회한다.")
    @Test
    void 프로덕트_리스트를_조회한다() throws Exception {
        given(productService.findProducts())
            .willReturn(PRODUCT_리스트_조회_응답());

        mockMvc.perform(get("/api/v1/product"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("프로덕트를 연다.")
    @Test
    void 프로덕트를_연다() throws Exception {
        willDoNothing()
            .given(productService)
            .openProduct(프로덕트_아이디);

        mockMvc.perform(put("/api/v1/product/{id}", 프로덕트_아이디))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("프로덕트를 닫는다.")
    @Test
    void 프로덕트를_닫는다() throws Exception {
        willDoNothing()
            .given(productService)
            .closeProduct(프로덕트_아이디);

        mockMvc.perform(delete("/api/v1/product/{id}", 프로덕트_아이디))
            .andDo(print())
            .andExpect(status().isOk());
    }
}
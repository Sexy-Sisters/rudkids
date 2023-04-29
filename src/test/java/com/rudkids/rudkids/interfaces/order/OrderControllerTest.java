package com.rudkids.rudkids.interfaces.order;

import com.rudkids.rudkids.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.UUID;

import static com.rudkids.rudkids.common.fixtures.order.OrderControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest extends ControllerTest {

    @DisplayName("[주문-주문요청]")
    @Test
    void 주문을_한다() throws Exception {
        given(orderService.create(any(), any()))
            .willReturn(UUID.randomUUID());

        mockMvc.perform(post(ORDER_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ORDER_주문_요청())))
            .andDo(print())
            .andExpect(status().isOk());
    }
}
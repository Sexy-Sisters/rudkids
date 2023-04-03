package com.rudkids.rudkids.interfaces.item;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.item.ItemInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static com.rudkids.rudkids.common.fixtures.item.ItemControllerFixture.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ItemControllerTest extends ControllerTest {

    @DisplayName("아이템을 등록한다.")
    @Test
    void 아이템을_등록한다() throws Exception {
        willDoNothing()
            .given(itemService)
            .registerItem(any());

        mockMvc.perform(post(ITEM_DEFAULT_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ITEM_등록_요청()))
            ).andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("아이템_리스트를_조회한다.")
    @Test
    void 아이템_리스트를_조회한다() throws Exception {
        given(itemService.findItems(any()))
            .willReturn(ITEM_리스트_조회_응답());

        mockMvc.perform(get(ITEM_DEFAULT_URL+"/{productId}", 프로덕트_아이디))
            .andDo(print())
            .andExpect(status().isOk());
    }
}
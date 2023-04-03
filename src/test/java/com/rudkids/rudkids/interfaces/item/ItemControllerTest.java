package com.rudkids.rudkids.interfaces.item;

import com.rudkids.rudkids.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.rudkids.rudkids.common.fixtures.item.ItemControllerFixture.ITEM_DEFAULT_URL;
import static com.rudkids.rudkids.common.fixtures.item.ItemControllerFixture.ITEM_등록_요청;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
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

}
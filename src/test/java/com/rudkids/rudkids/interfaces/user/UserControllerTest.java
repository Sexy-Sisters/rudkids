package com.rudkids.rudkids.interfaces.user;

import com.rudkids.rudkids.interfaces.user.dto.UserRequest;
import com.rudkids.rudkids.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.rudkids.rudkids.common.fixtures.UserFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends ControllerTest {
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private static final String AUTHORIZATION_HEADER_VALUE = "Bearer aaaaaaaa.bbbbbbbb.cccccccc";

    @DisplayName("유저정보를 등록하면 상태코드 200을 반환한다.")
    @Test
    void 유저정보를_등록하면_상태코드_200을_반환한다() throws Exception {
        UserRequest.SignUp request = new UserRequest.SignUp(유저_나이, 유저_성별);
        willDoNothing()
                .given(userService)
                .update(any(), any());

        mockMvc.perform(post("/api/user/sign-up")
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("유저정보를 수정한다.")
    @Test
    void 유저정보를_수정한다() throws Exception {
        UserRequest.Update request = new UserRequest.Update(유저_나이, 유저_성별);
        willDoNothing()
                .given(userService)
                .update(any(), any());

        mockMvc.perform(put("/api/user/update")
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
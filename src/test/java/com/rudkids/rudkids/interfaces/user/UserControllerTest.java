package com.rudkids.rudkids.interfaces.user;

import com.rudkids.rudkids.domain.user.exception.InvalidAgeRangeException;
import com.rudkids.rudkids.domain.user.exception.InvalidGenderException;
import com.rudkids.rudkids.interfaces.user.dto.UserRequest;
import com.rudkids.rudkids.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.rudkids.rudkids.common.fixtures.UserFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends ControllerTest {
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private static final String AUTHORIZATION_HEADER_VALUE = "Bearer aaaaaaaa.bbbbbbbb.cccccccc";

    @DisplayName("유저정보를 등록한다.")
    @Test
    void 유저정보를_등록한다() throws Exception {
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

    @DisplayName("잘못된 나이를 입력하고 유저정보를 등록하면 상태코드 400 Bad Request를 반환한다.")
    @Test
    void 잘못된_나이를_입력하고_유저정보를_등록하면_상태코드_400_Bad_Request를_반환한다() throws Exception {
        UserRequest.SignUp request = new UserRequest.SignUp(잘못된_유저_나이, 유저_성별);

        doThrow(new InvalidAgeRangeException(), new InvalidGenderException())
                .when(userService)
                .update(any(), any());

        mockMvc.perform(post("/api/user/sign-up")
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("잘못된 성별을 입력하고 유저정보를 등록하면 상태코드 400 Bad Request를 반환한다.")
    @Test
    void 잘못된_성별을_입력하고_유저정보를_등록하면_상태코드_400_Bad_Request를_반환한다() throws Exception {
        UserRequest.SignUp request = new UserRequest.SignUp(유저_나이, 잘못된_유저_성별);

        doThrow(new InvalidAgeRangeException(), new InvalidGenderException())
                .when(userService)
                .update(any(), any());

        mockMvc.perform(post("/api/user/sign-up")
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
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

    @DisplayName("잘못된 나이를 입력하고 유저정보를 수정하면 상태코드 400 Bad Request를 반환한다.")
    @Test
    void 잘못된_나이를_입력하고_유저정보를_수정하면_상태코드_400_Bad_Request를_반환한다() throws Exception {
        UserRequest.Update request = new UserRequest.Update(잘못된_유저_나이, 유저_성별);

        doThrow(new InvalidAgeRangeException(), new InvalidGenderException())
                .when(userService)
                .update(any(), any());

        mockMvc.perform(put("/api/user/update")
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("잘못된 성별을 입력하고 유저정보를 수정하면 상태코드 400 Bad Request를 반환한다.")
    @Test
    void 잘못된_성별을_입력하고_유저정보를_수정하면_상태코드_400_Bad_Request를_반환한다() throws Exception {
        UserRequest.Update request = new UserRequest.Update(유저_나이, 잘못된_유저_성별);

        doThrow(new InvalidAgeRangeException(), new InvalidGenderException())
                .when(userService)
                .update(any(), any());

        mockMvc.perform(put("/api/user/update")
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
package com.rudkids.rudkids.interfaces.user;

import com.rudkids.rudkids.domain.user.exception.InvalidAgeRangeException;
import com.rudkids.rudkids.domain.user.exception.InvalidGenderException;
import com.rudkids.rudkids.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.rudkids.rudkids.common.fixtures.user.UserControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends ControllerTest {

    @DisplayName("유저정보를 등록한다.")
    @Test
    void 유저정보를_등록한다() throws Exception {
        willDoNothing()
                .given(userService)
                .update(any(), any());

        mockMvc.perform(post("/api/user/sign-up")
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_회원가입_요청())))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("잘못된 나이를 입력하고 유저정보를 등록하면 상태코드 400 반환")
    @Test
    void 잘못된_나이를_입력하고_유저정보를_등록하면_상태코드_400_반환() throws Exception {
        doThrow(new InvalidAgeRangeException())
                .when(userService)
                .update(any(), any());

        mockMvc.perform(post("/api/user/sign-up")
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_잘못된_나이_회원가입_요청())))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("잘못된 성별을 입력하고 유저정보를 등록하면 상태코드 400 반환")
    @Test
    void 잘못된_성별을_입력하고_유저정보를_등록하면_상태코드_400_반환() throws Exception {
        doThrow(new InvalidGenderException())
                .when(userService)
                .update(any(), any());

        mockMvc.perform(post("/api/user/sign-up")
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_잘못된_성별_회원가입_요청())))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("유저정보를 수정한다.")
    @Test
    void 유저정보를_수정한다() throws Exception {
        willDoNothing()
                .given(userService)
                .update(any(), any());

        mockMvc.perform(put("/api/user/update")
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_정보_수정_요청())))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("잘못된 나이를 입력하고 유저정보를 수정하면 상태코드 400 반환")
    @Test
    void 잘못된_나이를_입력하고_유저정보를_수정하면_상태코드_400_반환() throws Exception {
        doThrow(new InvalidAgeRangeException())
                .when(userService)
                .update(any(), any());

        mockMvc.perform(put("/api/user/update")
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_잘못된_유저_나이_정보_수정_요청())))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("잘못된 성별을 입력하고 유저정보를 수정하면 상태코드 400 반환")
    @Test
    void 잘못된_성별을_입력하고_유저정보를_수정하면_상태코드_400_반환() throws Exception {
        doThrow(new InvalidGenderException())
                .when(userService)
                .update(any(), any());

        mockMvc.perform(put("/api/user/update")
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_잘못된_유저_성별_정보_수정_요청())))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
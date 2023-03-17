package com.rudkids.rudkids.domain.user.service;

import com.rudkids.rudkids.domain.user.domain.*;
import com.rudkids.rudkids.domain.user.dto.request.SignUpRequestDto;
import com.rudkids.rudkids.domain.user.exception.DuplicateEmailException;
import com.rudkids.rudkids.domain.user.repository.UserRepository;
import com.rudkids.rudkids.util.ServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ServiceTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void inputData() {
        User user = User.builder()
                .email(Email.create("test@gmail.com"))
                .password(Password.create("test1234"))
                .name(Name.create("남세"))
                .phoneNumber(PhoneNumber.create("010-1234-5678"))
                .build();
        userRepository.save(user);
    }

    @DisplayName("이미 존재하는 이메일로 회원가입할 시 예외 발생")
    @Test
    void create_Exception_Duplicated_Email() {
        SignUpRequestDto signUpRequest = SignUpRequestDto.builder()
                .email("test@gmail.com")
                .password("test1234")
                .name("남세")
                .phoneNumber("010-1234-5678")
                .build();

        assertThatThrownBy(() -> userService.signUp(signUpRequest))
                .isInstanceOf(DuplicateEmailException.class);
    }

    @DisplayName("모든 조건 만족 시 회원가입 성공")
    @Test
    void signUp() {
        SignUpRequestDto signUpRequest = SignUpRequestDto.builder()
                .email("newTest@gmail.com")
                .password("test1234")
                .name("남세")
                .phoneNumber("010-9876-5432")
                .build();
        userService.signUp(signUpRequest);

        User findUser = userRepository.findByEmailValueAndPasswordValue(signUpRequest.getEmail(),
                signUpRequest.getPassword());
        assertThat(findUser.getRoleType()).isEqualTo(RoleType.USER);
    }

}
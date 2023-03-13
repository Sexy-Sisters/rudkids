package com.rudkids.rudkids.user.domain;

import com.rudkids.rudkids.domain.user.domain.Password;
import com.rudkids.rudkids.domain.user.exception.InvalidPasswordException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PasswordTest {

    @DisplayName("숫자, 알파벳 대소문자가 없거나 8자리에서 20자리가 아닌 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"adfasdfadsfa", "12412412421", "1231ds", "312fsfsdf2f23ff23f23f323f13f3"})
    void create_Exception_Format(String invalid) {
        assertThatThrownBy(() -> Password.create(invalid))
                .isInstanceOf(InvalidPasswordException.class);
    }

}
package com.rudkids.rudkids.user.domain;

import com.rudkids.rudkids.user.exception.InvalidEmailFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class EmailTest {

    @DisplayName("이메일 양식이 아닐 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"3421432@", "2142@gmail.co"})
    void create_Exception_Format(String invalid) {
        assertThatThrownBy(() -> Email.create(invalid))
                .isInstanceOf(InvalidEmailFormatException.class);
    }

}
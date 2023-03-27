package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.domain.user.exception.InvalidGenderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class GenderTest {

    @DisplayName("성별 양식에 맞지 않을 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"Male", "Female", " "})
    @NullSource
    @EmptySource
    void create_Exception_Format(String invalid) {
        assertThatThrownBy(() -> Gender.toEnum(invalid))
                .isInstanceOf(InvalidGenderException.class);
    }
}
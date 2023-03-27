package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.domain.user.exception.InvalidAgeRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class AgeTest {

    @DisplayName("길이가 2미만이거나 100초과라면 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {1, 101})
    void create_Exception_Range(int invalid) {
        assertThatThrownBy(() -> Age.create(invalid))
                .isInstanceOf(InvalidAgeRangeException.class);
    }
}
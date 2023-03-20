package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.domain.user.exception.InvalidNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NameTest {

    @DisplayName("길이가 2자에서 20자 사이가 아니라면 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "남", "남남남남남남남남남남남남남남남남남남남남남"})
    @NullSource
    @EmptySource
    void create_Exception_Length(String invalid) {
        assertThatThrownBy(() -> Name.create(invalid))
                .isInstanceOf(InvalidNameException.class);
    }
}
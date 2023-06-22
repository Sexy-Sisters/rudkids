package com.rudkids.core.product.domain;

import com.rudkids.core.product.exception.InvalidProductTitleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class TitleTest {

    @DisplayName("길이가 20자 초과 시 예외 발생")
    @Test
    void create_Exception_ContentLength() {
        String invalid = "a".repeat(21);
        assertThatThrownBy(() -> Title.create(invalid))
            .isInstanceOf(InvalidProductTitleException.class);
    }

    @DisplayName("제목 내용이 없을 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @NullSource
    void create_Exception_NoContent(String invalid) {
        assertThatThrownBy(() -> Title.create(invalid))
            .isInstanceOf(InvalidProductTitleException.class);
    }
}
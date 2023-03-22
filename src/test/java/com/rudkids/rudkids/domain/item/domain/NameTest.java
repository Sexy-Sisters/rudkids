package com.rudkids.rudkids.domain.item.domain;

import com.rudkids.rudkids.domain.item.exception.InvalidItemNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class NameTest {

    @DisplayName("이름 20자 초과 시 예외 발생")
    @Test
    void create_Exception_ContentLength() {
        String invalid = "a".repeat(21);
        assertThatThrownBy(() -> Name.create(invalid))
            .isInstanceOf(InvalidItemNameException.class);
    }

    @DisplayName("이름 내용이 없을 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @NullSource
    void create_Exception_NoContent(String invalid) {
        assertThatThrownBy(() -> Name.create(invalid))
            .isInstanceOf(InvalidItemNameException.class);
    }
}

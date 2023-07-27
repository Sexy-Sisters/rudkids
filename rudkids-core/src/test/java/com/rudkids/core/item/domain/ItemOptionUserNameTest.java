package com.rudkids.core.item.domain;

import com.rudkids.core.item.domain.option.ItemOptionName;
import com.rudkids.core.item.exception.InvalidItemOptionNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ItemOptionUserNameTest {

    @DisplayName("이름이 20자 초과시 예외 발생")
    @Test
    void create_Exception_ContentLength() {
        String invalid = "a".repeat(21);
        assertThatThrownBy(() -> ItemOptionName.create(invalid))
            .isInstanceOf(InvalidItemOptionNameException.class);
    }

    @DisplayName("이름이 빈 값일 때 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @NullSource
    void create_Exception_NoContent(String invalid) {
        assertThatThrownBy(() -> ItemOptionName.create(invalid))
            .isInstanceOf(InvalidItemOptionNameException.class);
    }

}
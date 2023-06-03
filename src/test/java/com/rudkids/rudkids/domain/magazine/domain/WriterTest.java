package com.rudkids.rudkids.domain.magazine.domain;

import com.rudkids.rudkids.domain.magazine.exception.InvalidMagazineWriterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class WriterTest {

    @DisplayName("작성자 10자를 초과할 경우 예외가 발생한다.")
    @Test
    void create_Exception_ContentLength() {
        String invalid = "a".repeat(11);
        assertThatThrownBy(() -> Writer.create(invalid))
            .isInstanceOf(InvalidMagazineWriterException.class);
    }

    @DisplayName("내용이 없을 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @NullSource
    void create_Exception_NoContent(String invalid) {
        assertThatThrownBy(() -> Writer.create(invalid))
            .isInstanceOf(InvalidMagazineWriterException.class);
    }
}

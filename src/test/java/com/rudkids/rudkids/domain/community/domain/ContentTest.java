package com.rudkids.rudkids.domain.community.domain;

import com.rudkids.rudkids.domain.community.exception.InvalidCommunityContentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ContentTest {

    @DisplayName("내용 50자를 초과할 경우 예외가 발생한다.")
    @Test
    void create_Exception_ContentLength() {
        String invalid = "a".repeat(10001);
        assertThatThrownBy(() -> Content.create(invalid))
                .isInstanceOf(InvalidCommunityContentException.class);
    }

    @DisplayName("내용이 없을 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @NullSource
    void create_Exception_NoContent(String invalid) {
        assertThatThrownBy(() -> Content.create(invalid))
                .isInstanceOf(InvalidCommunityContentException.class);
    }
}
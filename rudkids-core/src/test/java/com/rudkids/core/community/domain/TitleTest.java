package com.rudkids.core.community.domain;

import com.rudkids.core.community.exception.InvalidCommunityTitleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class TitleTest {

    @DisplayName("제목 50자를 초과할 경우 예외가 발생한다")
    @Test
    void 제목_50자를_초과할_경우_예외가_발생한다() {
        String invalid = "a".repeat(51);
        assertThatThrownBy(() -> Title.create(invalid))
                .isInstanceOf(InvalidCommunityTitleException.class);
    }

    @DisplayName("제목 내용이 없을 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @NullSource
    void 제목_내용이_없을_경우_예외가_발생한다(String invalid) {
        assertThatThrownBy(() -> Title.create(invalid))
                .isInstanceOf(InvalidCommunityTitleException.class);
    }
}
package com.rudkids.core.community.domain;

import com.rudkids.core.community.exception.InvalidCommunityTitleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class TitleTest {

    @Nested
    @DisplayName("제목을 등록한다")
    class create {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            final String title = "제목";

            //when
            var generatedTitle = Title.create(title);

            //then
            assertThat(generatedTitle.getValue()).isEqualTo("제목");
        }

        @Test
        @DisplayName("실패: 길이 초과")
        void fail() {
            //given
            String invalid = "a".repeat(51);

            //when, then
            assertThatThrownBy(() -> Title.create(invalid))
                .isInstanceOf(InvalidCommunityTitleException.class);
        }

        @NullSource
        @ParameterizedTest
        @ValueSource(strings = {"", " "})
        @DisplayName("실페: 비어있는 제목")
        void fail2(String invalid) {
            assertThatThrownBy(() -> Title.create(invalid))
                .isInstanceOf(InvalidCommunityTitleException.class);
        }
    }
}
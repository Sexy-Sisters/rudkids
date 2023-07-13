package com.rudkids.core.community.domain;

import com.rudkids.core.community.exception.InvalidCommunityContentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class ContentTest {

    @Nested
    @DisplayName("내용을 등록한다")
    class create {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            final String content = "내용";

            //when
            var generatedContent = Content.create(content);

            //then
            assertThat(generatedContent.getValue()).isEqualTo("내용");
        }

        @Test
        @DisplayName("실패: 길이 초과")
        void fail() {
            //given
            String invalid = "a".repeat(10001);

            //when, then
            assertThatThrownBy(() -> Content.create(invalid))
                .isInstanceOf(InvalidCommunityContentException.class);
        }

        @NullSource
        @ParameterizedTest
        @ValueSource(strings = {"", " "})
        @DisplayName("실패: 비어있는 내용")
        void fail2(String invalid) {
            //given

            //when, then
            assertThatThrownBy(() -> Content.create(invalid))
                .isInstanceOf(InvalidCommunityContentException.class);
        }
    }
}
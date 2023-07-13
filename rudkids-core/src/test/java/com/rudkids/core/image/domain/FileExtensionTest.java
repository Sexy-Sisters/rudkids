package com.rudkids.core.image.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FileExtensionTest {

    @Nested
    @DisplayName("확장자가 지원되는지 검증한다")
    class isSupport {

        @ParameterizedTest
        @ValueSource(strings = {"jpg", "jpeg", "png", "bmp"})
        @DisplayName("성공")
        void success(String extension) {
            boolean actual = FileExtension.isSupport(extension);
            assertThat(actual).isTrue();
        }

        @Test
        @DisplayName("실패: 지원하지 않는 확장자")
        void fail() {
            //given
            final String notSupportExtension = "hepg";

            //when
            boolean actual = FileExtension.isSupport(notSupportExtension);

            //then
            assertThat(actual).isFalse();
        }
    }
}

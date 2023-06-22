package com.rudkids.core.image.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FileExtensionTest {

    @DisplayName("지원하는 확장자를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"jpg", "jpeg", "png", "bmp"})
    void 지원하는_확장자를_반환한다(String extension) {
        boolean actual = FileExtension.isSupport(extension);
        assertThat(actual).isTrue();
    }
}

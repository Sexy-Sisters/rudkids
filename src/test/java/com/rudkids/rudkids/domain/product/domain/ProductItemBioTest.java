package com.rudkids.rudkids.domain.product.domain;

import com.rudkids.rudkids.domain.product.exception.InvalidBioException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ProductItemBioTest {

    @DisplayName("길이가 1000자 이상이면 예외 발생")
    @Test
    void create_Exception_ContentLength() {
        String invalid = "a".repeat(1001);
        assertThatThrownBy(() -> ProductBio.create(invalid))
            .isInstanceOf(InvalidBioException.class);
    }

    @DisplayName("소개글 내용이 없을 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @NullSource
    void create_Exception_NoContent(String invalid) {
        assertThatThrownBy(() -> ProductBio.create(invalid))
            .isInstanceOf(InvalidBioException.class);
    }

}
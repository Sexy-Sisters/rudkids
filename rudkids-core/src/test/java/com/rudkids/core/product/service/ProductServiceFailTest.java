package com.rudkids.core.product.service;

import com.rudkids.core.common.fixtures.ProductServiceFixtures;
import com.rudkids.core.product.domain.ProductStatus;
import com.rudkids.core.product.exception.ProductNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductServiceFailTest extends ProductServiceFixtures {

    @DisplayName("[프로덕트-상태변경-ProductNotFoundException]")
    @Test
    void 존재하지_않는_프로덕트의_상태_변경시_예외가_발생한다() {
        // Given
        var status = "OPEN";
        var productId = UUID.randomUUID();

        // When & Then
        assertThatThrownBy(() -> productService.changeStatus(productId, status))
            .isInstanceOf(ProductNotFoundException.class);
    }
}

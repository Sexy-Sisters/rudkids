package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import com.rudkids.rudkids.domain.product.exception.ProductNotFoundException;
import com.rudkids.rudkids.domain.user.exception.NotAdminRoleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductServiceFailTest extends ProductServiceTest {

    @DisplayName("[프로덕트-상태변경-ProductNotFoundException]")
    @Test
    void 존재하지_않는_프로덕트의_상태_변경시_예외가_발생한다() {
        // Given
        var status = ProductStatus.OPEN;
        var productId = UUID.randomUUID();
        var userId = user.getId();

        // When & Then
        assertThatThrownBy(() -> productService.changeStatus(status, productId))
            .isInstanceOf(ProductNotFoundException.class);
    }

    @DisplayName("[프로덕트-상태변경-NotAdminRoleException]")
    @Test
    void 관리자가_아닌_유저가_상태_변경시_예외가_발생한다() {
        // Given
        var status = ProductStatus.OPEN;
        var productId = UUID.randomUUID();
        var userId = user.getId();
        user.changeAuthorityUser();

        // When & Then
        assertThatThrownBy(() -> productService.changeStatus(status, productId))
            .isInstanceOf(NotAdminRoleException.class);
    }
}

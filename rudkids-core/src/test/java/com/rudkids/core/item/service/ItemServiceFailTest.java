package com.rudkids.core.item.service;

import com.rudkids.core.common.fixtures.item.ItemServiceFixtures;
import com.rudkids.core.item.exception.ItemNotFoundException;
import com.rudkids.core.product.exception.ProductNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ItemServiceFailTest extends ItemServiceFixtures {

    @DisplayName("[아이템-상세조회-ItemNotFoundException]")
    @Test
    void 존재하지_않는_아이템_상세_조회시_예외가_발생한다() {
        // Given
        var invalidItemName = "invalid";

        // When & Then
        assertThatThrownBy(() -> itemService.get(invalidItemName))
            .isInstanceOf(ItemNotFoundException.class);
    }
}

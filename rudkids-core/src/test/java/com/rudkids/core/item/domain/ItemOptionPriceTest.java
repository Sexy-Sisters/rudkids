package com.rudkids.core.item.domain;

import com.rudkids.core.item.domain.option.ItemOptionPrice;
import com.rudkids.core.item.exception.InvalidItemOptionPriceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ItemOptionPriceTest {

    @DisplayName("값이 0 미만이면 예외 발생")
    @Test
    void create_Exception_Size() {
        int invalid = -1;
        assertThatThrownBy(() -> ItemOptionPrice.create(invalid))
            .isInstanceOf(InvalidItemOptionPriceException.class);
    }
}
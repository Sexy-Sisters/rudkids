package com.rudkids.rudkids.domain.item.domain;

import com.rudkids.rudkids.domain.item.domain.item.Quantity;
import com.rudkids.rudkids.domain.item.exception.InvalidItemQuantityException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class QuantityTest {

    @DisplayName("값이 0 미만이면 예외 발생")
    @Test
    void create_Exception_Size() {
        int invalid = -1;
        assertThatThrownBy(() -> Quantity.create(invalid))
            .isInstanceOf(InvalidItemQuantityException.class);
    }
}

package com.rudkids.rudkids.domain.order.domain.orderItem.orderItemOption;

import com.rudkids.rudkids.domain.item.domain.itemOption.ItemOptionPrice;
import com.rudkids.rudkids.domain.item.exception.InvalidItemOptionPriceException;
import com.rudkids.rudkids.domain.order.exception.InvalidOrderItemOptionPriceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class OrderItemOptionPriceTest {

    @DisplayName("값이 0 미만이면 예외 발생")
    @Test
    void create_Exception_Size() {
        int invalid = -1;
        assertThatThrownBy(() -> OrderItemOptionPrice.create(invalid))
            .isInstanceOf(InvalidOrderItemOptionPriceException.class);
    }
}
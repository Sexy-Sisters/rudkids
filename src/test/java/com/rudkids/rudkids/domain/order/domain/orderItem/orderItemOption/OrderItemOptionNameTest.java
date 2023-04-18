package com.rudkids.rudkids.domain.order.domain.orderItem.orderItemOption;

import com.rudkids.rudkids.domain.item.domain.itemOption.ItemOptionName;
import com.rudkids.rudkids.domain.item.exception.InvalidItemOptionNameException;
import com.rudkids.rudkids.domain.order.exception.InvalidOrderItemOptionGroupNameException;
import com.rudkids.rudkids.domain.order.exception.InvalidOrderItemOptionNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class OrderItemOptionNameTest {

    @DisplayName("이름이 20자 초과시 예외 발생")
    @Test
    void create_Exception_ContentLength() {
        String invalid = "a".repeat(21);
        assertThatThrownBy(() -> OrderItemOptionName.create(invalid))
            .isInstanceOf(InvalidOrderItemOptionNameException.class);
    }

    @DisplayName("이름이 빈 값일 때 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @NullSource
    void create_Exception_NoContent(String invalid) {
        assertThatThrownBy(() -> OrderItemOptionName.create(invalid))
            .isInstanceOf(InvalidOrderItemOptionNameException.class);
    }

}
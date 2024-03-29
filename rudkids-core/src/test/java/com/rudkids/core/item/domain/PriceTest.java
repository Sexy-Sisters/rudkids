package com.rudkids.core.item.domain;

import com.rudkids.core.item.exception.InvalidItemPriceException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PriceTest {

    @DisplayName("값이 0 미만이면 예외 발생")
    @Test
    void create_Exception_Size() {
        int invalid = -1;
        AssertionsForClassTypes.assertThatThrownBy(() -> Price.create(invalid))
            .isInstanceOf(InvalidItemPriceException.class);
    }
}

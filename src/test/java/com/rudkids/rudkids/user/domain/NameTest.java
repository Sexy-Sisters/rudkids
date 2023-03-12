package com.rudkids.rudkids.user.domain;

import com.rudkids.rudkids.user.exception.InvalidNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NameTest {

    @DisplayName("길이가 20자 초과 시 예외 발생")
    @Test
    void create_Exception_Length() {
        String invalid = "a".repeat(21);
        assertThatThrownBy(() -> Name.create(invalid))
                .isInstanceOf(InvalidNameException.class);
    }

}
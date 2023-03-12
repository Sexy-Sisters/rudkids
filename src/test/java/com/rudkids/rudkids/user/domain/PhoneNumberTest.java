package com.rudkids.rudkids.user.domain;

import com.rudkids.rudkids.user.exception.InvalidPhoneNumberFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PhoneNumberTest {

    @DisplayName("폰 양식에 맞지 않을 시 예외 발생")
    @Test
    void create_Exception_Format() {
        String invalid = "01012345678";
        assertThatThrownBy(() -> PhoneNumber.create(invalid))
                .isInstanceOf(InvalidPhoneNumberFormatException.class);
    }
}
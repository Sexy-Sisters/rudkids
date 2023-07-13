package com.rudkids.core.delivery.domain;

import com.rudkids.core.user.exception.InvalidNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AddressTest {

    @Nested
    @DisplayName("주소를 등록한다")
    class create {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            Address address = Address.create("부산광역시", "서구", "02348");

            //when, then
            assertAll(() -> {
                assertThat(address.getAddress()).isEqualTo("부산광역시");
                assertThat(address.getExtraAddress()).isEqualTo("서구");
                assertThat(address.getZipCode()).isEqualTo("02348");
            });
        }

        @NullSource
        @ParameterizedTest
        @ValueSource(strings = {"", " "})
        @DisplayName("실퍠: 잘못된 주소값")
        void fail(String invalid) {
            //given
            
            //when, then
            assertThatThrownBy(() -> Address.create(invalid, invalid, invalid))
                .isInstanceOf(InvalidNameException.class);
        }
    }
}

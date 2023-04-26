package com.rudkids.rudkids.domain.item.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ItemTest {

    @DisplayName("아이템 업데이트")
    @Test
    void 아이템_업테이트() {
        // Given
        var name = Name.create("아이템");
        var itemBio = ItemBio.create("아이템 소개글");
        var price = Price.create(1000);
        var quantity = Quantity.create(100);
        var limitType = LimitType.LIMITED;
        var item = Item.create(name, itemBio, price, quantity, limitType);

        var updatedName = Name.create("업데이트된 아이템");
        var updatedItemBio = ItemBio.create("업데이트된 아이템 소개글");
        var updatedPrice = Price.create(10001);
        var updatedQuantity = Quantity.create(1100);
        var updatedLimitType = LimitType.NORMAL;

        // When
        item.update(
            updatedName,
            updatedItemBio,
            updatedPrice,
            updatedQuantity,
            updatedLimitType
        );

        // Then
        assertAll(
            () -> assertThat(item.getName()).isEqualTo("업데이트된 아이템"),
            () -> assertThat(item.getItemBio()).isEqualTo("업데이트된 아이템 소개글"),
            () -> assertThat(item.getPrice()).isEqualTo(10001),
            () -> assertThat(item.getQuantity()).isEqualTo(1100),
            () -> assertThat(item.getLimitType()).isEqualTo(LimitType.NORMAL)
        );
    }
}
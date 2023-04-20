package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.common.fixtures.item.ItemServiceFixtures;
import com.rudkids.rudkids.domain.item.ItemInfo;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


public class ItemServiceTest extends ItemServiceFixtures {

    @DisplayName("상품 등록 성공")
    @Test
    void registerItem() {
        itemService.create(ITEM_등록_요청, product.getId(), user.getId());

        Item findItem = itemReader.getItem(ITEM_등록_요청.name());
        assertAll(
            () -> assertThat(findItem.getName()).isEqualTo("Red Pill"),
            () -> assertThat(findItem.getPrice()).isEqualTo(1_000_000),
            () -> assertThat(findItem.getQuantity()).isEqualTo(1),
            () -> assertThat(findItem.getItemBio()).isEqualTo("소개글입니다~"),
            () -> assertThat(findItem.getLimitType()).isEqualTo(LimitType.LIMITED),
            () -> assertThat(findItem.getItemStatus()).isEqualTo(ItemStatus.ON_SALES),
            () -> assertThat(findItem.getItemOptionGroups()).hasSize(1)
        );
        var itemOptionGroup = findItem.getItemOptionGroups().get(0);
        assertAll(() -> {
            assertThat(itemOptionGroup.getOrdering()).isEqualTo(1);
            assertThat(itemOptionGroup.getItemOptionGroupName()).isEqualTo("사이즈");
            assertThat(itemOptionGroup.getItemOptions()).hasSize(3);
        });
    }

    @DisplayName("아이템 상세 조회")
    @Test
    void findItemDetail() {
        ItemInfo.Detail findItem = itemService.find(item.getId());

        assertAll(
            () -> assertThat(findItem.name()).isEqualTo("No.1"),
            () -> assertThat(findItem.price()).isEqualTo(2_990),
            () -> assertThat(findItem.quantity()).isEqualTo(1_000),
            () -> assertThat(findItem.itemBio()).isEqualTo("소개글입니다~"),
            () -> assertThat(findItem.limitType()).isEqualTo(LimitType.LIMITED)
        );
    }

    @DisplayName("아이템 판매 종료")
    @Test
    void changeOnSales() {
        String itemStatus = itemService.changeOnSales(item.getId());
        assertThat(itemStatus).isEqualTo("ON_SALES");
    }

    @DisplayName("아이템 판매 종료")
    @Test
    void changeEndOfSales() {
        String itemStatus = itemService.changeEndOfSales(item.getId());
        assertThat(itemStatus).isEqualTo("END_OF_SALES");
    }
}

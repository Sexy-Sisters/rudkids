package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.common.fixtures.item.ItemServiceFixtures;
import com.rudkids.rudkids.domain.item.ItemInfo;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import com.rudkids.rudkids.domain.item.exception.ItemNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ItemServiceTest extends ItemServiceFixtures {

    @DisplayName("상품 등록")
    @Test
    void create() {
        itemService.create(ITEM_등록_요청, product.getId(), user.getId());

        Item findItem = itemReader.getItem(ITEM_등록_요청.name());
        assertAll(
            () -> assertThat(findItem.getName()).isEqualTo("Red Pill"),
            () -> assertThat(findItem.getPrice()).isEqualTo(1_000_000),
            () -> assertThat(findItem.getQuantity()).isEqualTo(1),
            () -> assertThat(findItem.getItemBio()).isEqualTo("소개글입니다~"),
            () -> assertThat(findItem.getLimitType()).isEqualTo(LimitType.LIMITED),
            () -> assertThat(findItem.getItemStatus()).isEqualTo(ItemStatus.SELLING),
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
    void find() {
        ItemInfo.Detail findItem = itemService.find(item.getId());

        assertAll(
            () -> assertThat(findItem.name()).isEqualTo("No.1"),
            () -> assertThat(findItem.price()).isEqualTo(2_990),
            () -> assertThat(findItem.quantity()).isEqualTo(1_000),
            () -> assertThat(findItem.itemBio()).isEqualTo("소개글입니다~"),
            () -> assertThat(findItem.limitType()).isEqualTo(LimitType.LIMITED)
        );
    }

    @DisplayName("아이템 업데이트")
    @Test
    void update() {
        // Given
        var itemId = item.getId();
        var userId = user.getId();

        // When
        itemService.update(아이템_수정_요청(), itemId, userId);

        // Then
        assertAll(
            () -> assertThat(item.getName()).isEqualTo("아이템"),
            () -> assertThat(item.getItemBio()).isEqualTo("소개글"),
            () -> assertThat(item.getPrice()).isEqualTo(1000),
            () -> assertThat(item.getQuantity()).isEqualTo(100),
            () -> assertThat(item.getLimitType()).isEqualTo(LimitType.LIMITED)
        );
    }

    @DisplayName("아이템 상태 판매 중으로 변경")
    @Test
    void changeSelling() {
        // Given
        var itemId = item.getId();
        var userId = user.getId();
        var itemStatus = ItemStatus.SELLING;

        // When
        var changedStatus = itemService.changeItemStatus(itemId, itemStatus, userId);

        // Then
        assertThat(changedStatus).isEqualTo(ItemStatus.SELLING);
    }

    @DisplayName("아이템 상태 판매 완료로 변경")
    @Test
    void changeSoldOut() {
        // Given
        var itemId = item.getId();
        var userId = user.getId();
        var itemStatus = ItemStatus.SOLD_OUT;

        // When
        var changedStatus = itemService.changeItemStatus(itemId, itemStatus, userId);

        // Then
        assertThat(changedStatus).isEqualTo(ItemStatus.SOLD_OUT);
    }

    @DisplayName("아이템 상태 준비 중으로 변경")
    @Test
    void changePreparing() {
        // Given
        var itemId = item.getId();
        var userId = user.getId();
        var itemStatus = ItemStatus.PREPARING;

        // When
        var changedStatus = itemService.changeItemStatus(itemId, itemStatus, userId);

        // Then
        assertThat(changedStatus).isEqualTo(ItemStatus.PREPARING);
    }

    @DisplayName("아이템 삭제")
    @Test
    void delete() {
        // Given
        var itemId = item.getId();
        var userId = user.getId();

        // When
        itemService.delete(itemId, userId);

        // Then
        assertThatThrownBy(() -> itemReader.getItem(itemId))
            .isInstanceOf(ItemNotFoundException.class);
    }
}

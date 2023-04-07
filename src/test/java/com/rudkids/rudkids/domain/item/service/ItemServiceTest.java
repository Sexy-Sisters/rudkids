package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.common.fixtures.item.ItemServiceFixtures;
import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemInfo;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


public class ItemServiceTest extends ItemServiceFixtures {

    @DisplayName("상품 등록 성공")
    @Test
    void registerItem() {
        itemService.registerItem(ITEM_등록_요청);

        Item findItem = itemReader.getItem(ITEM_등록_요청.name());
        assertAll(
            () -> assertThat(findItem.getName()).isEqualTo("Red Pill"),
            () -> assertThat(findItem.getPrice()).isEqualTo(1_000_000),
            () -> assertThat(findItem.getQuantity()).isEqualTo(1),
            () -> assertThat(findItem.getItemBio()).isEqualTo("소개글입니다~"),
            () -> assertThat(findItem.getLimitType()).isEqualTo(LimitType.LIMITED),
            () -> assertThat(findItem.getItemStatus()).isEqualTo(ItemStatus.ON_SALES)
            // 단방향 매핑이어서 리스트 관련 테스트 불가
            //  () -> assertThat(findItem.getItemOptionGroups()).hasSize(1)
        );
//        assertAll(() -> {
//            ItemOptionGroup itemOptionGroup = findItem.getItemOptionGroups().get(0);
//            assertThat(itemOptionGroup.getOrdering()).isEqualTo(1);
//            assertThat(itemOptionGroup.getItemOptionGroupName()).isEqualTo("사이즈");
//            assertThat(itemOptionGroup.getItemOptions()).hasSize(3);
//        });
    }

    @DisplayName("특정 프로덕트의 아이템 리스트 조회")
    @Test
    void findItems() {
        List<ItemCommand.RegisterItemRequest> commandList = List.of(
            ItemCommand.RegisterItemRequest.builder()
                .productId(product.getId())
                .name("No.2")
                .price(2_990)
                .quantity(1_000)
                .itemBio("소개글입니다~")
                .limitType(LimitType.NORMAL)
                .build(),
            ItemCommand.RegisterItemRequest.builder()
                .productId(product.getId())
                .name("No.3")
                .price(2_990)
                .quantity(1_000)
                .itemBio("소개글입니다~")
                .limitType(LimitType.NORMAL)
                .build()
        );
        commandList.forEach(itemService::registerItem);

        List<ItemInfo.Main> items = itemService.findItems(product.getId());
        assertThat(items).hasSize(2);
    }

    @DisplayName("아이템 상세 조회")
    @Test
    void findItemDetail() {
        ItemInfo.Detail findItem = itemService.findItemDetail(item.getId());

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
    void openItem() {
        String itemStatus = itemService.openItem(item.getId());
        assertThat(itemStatus).isEqualTo("ON_SALES");
    }

    @DisplayName("아이템 판매 종료")
    @Test
    void closeItem() {
        String itemStatus = itemService.closeItem(item.getId());
        assertThat(itemStatus).isEqualTo("END_OF_SALES");
    }
}

package com.rudkids.core.item.service;

import com.rudkids.core.common.fixtures.ItemServiceFixtures;
import com.rudkids.core.item.domain.Item;
import com.rudkids.core.item.domain.ItemStatus;
import com.rudkids.core.item.domain.LimitType;
import com.rudkids.core.item.dto.ItemRequest;
import com.rudkids.core.item.dto.ItemResponse;
import com.rudkids.core.item.exception.ItemNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ItemServiceTest extends ItemServiceFixtures {

    @DisplayName("[아이템-생성]")
    @Test
    void 아이템을_생성한다() {
        UUID itemId = itemService.create(user.getId(), product.getId(), ITEM_등록_요청);

        Item findItem = itemRepository.get(itemId);
        assertAll(
            () -> assertThat(findItem.getEnName()).isEqualTo("Red Pill"),
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

    @DisplayName("[아이템-상세조회]")
    @Test
    void 아이템을_상세조회한다() {
        ItemResponse.Detail findItem = itemService.get(item.getEnName());

        assertAll(
            () -> assertThat(findItem.enName()).isEqualTo("No.1"),
            () -> assertThat(findItem.price()).isEqualTo(2_990),
            () -> assertThat(findItem.quantity()).isEqualTo(1_000),
            () -> assertThat(findItem.itemBio()).isEqualTo("소개글입니다~"),
            () -> assertThat(findItem.limitType()).isEqualTo(LimitType.LIMITED),
            () -> assertThat(findItem.imageUrls()).hasSize(2)
        );
    }

    @DisplayName("[아이템-인기있는아이템 조회]")
    @Test
    void 인기있는_아이템을_조회한다() {
        Pageable pageable = PageRequest.of(0, 5);
        var response = itemService.getPopularItems(pageable);

        assertThat(response).hasSize(1);
    }

    @DisplayName("[아이템-영상이미지 조회]")
    @Test
    void 아이템_영상이미지들을_조회한다() {
        var response = itemService.getItemVideoImages();

        var actual = response.get(0);
        assertAll(() -> {
            assertThat(actual.name()).isEqualTo("No.1");
            assertThat(actual.imageUrl()).isEqualTo("url");
        });
    }

    @DisplayName("[아이템-영상 조회]")
    @Test
    void 아이템_영상을_조회한다() {
        var actual = itemService.getItemVideo(item.getEnName());

        assertAll(() -> {
            assertThat(actual.name()).isEqualTo("No.1");
            assertThat(actual.videoUrl()).isEqualTo("videoUrl");
        });
    }

    @DisplayName("[아이템-수정]")
    @Test
    void 아이템을_수정한다() {
        // Given
        var itemId = item.getId();
        var userId = user.getId();

        // When
        itemService.update(userId, itemId, 아이템_수정_요청());

        // Then
        assertAll(
            () -> assertThat(item.getEnName()).isEqualTo("item"),
            () -> assertThat(item.getItemBio()).isEqualTo("소개글"),
            () -> assertThat(item.getPrice()).isEqualTo(1000),
            () -> assertThat(item.getQuantity()).isEqualTo(100),
            () -> assertThat(item.getLimitType()).isEqualTo(LimitType.LIMITED)
        );
    }

    @DisplayName("[아이템-상태변경-파는중]")
    @Test
    void 아이템의_상태를_파는중으로_변경한다() {
        // Given
        var itemId = item.getId();
        var userId = user.getId();
        var itemStatus = "SELLING";

        // When
        ItemRequest.ChangeStatus statusRequest = new ItemRequest.ChangeStatus(itemStatus);
        var changedStatus = itemService.changeStatus(userId, itemId, statusRequest);

        // Then
        assertThat(changedStatus).isEqualTo("SELLING");
    }

    @DisplayName("[아이템-상태변경-판매완료]")
    @Test
    void 아이템의_상태를_판매완료로_변경한다() {
        // Given
        var itemId = item.getId();
        var userId = user.getId();
        var itemStatus = "SOLD_OUT";

        // When
        ItemRequest.ChangeStatus statusRequest = new ItemRequest.ChangeStatus(itemStatus);
        var changedStatus = itemService.changeStatus(userId, itemId, statusRequest);

        // Then
        assertThat(changedStatus).isEqualTo("SOLD_OUT");
    }

    @DisplayName("[아이템-상태변경-준비중]")
    @Test
    void 아이템의_상태를_준비중으로_변경한다() {
        // Given
        var itemId = item.getId();
        var userId = user.getId();
        var itemStatus = "PREPARING";

        // When
        ItemRequest.ChangeStatus statusRequest = new ItemRequest.ChangeStatus(itemStatus);
        var changedStatus = itemService.changeStatus(userId, itemId, statusRequest);

        // Then
        assertThat(changedStatus).isEqualTo("PREPARING");
    }

    @DisplayName("[아이템-삭제]")
    @Test
    void 아이템을_삭제한다() {
        // Given
        var itemId = item.getId();
        var userId = user.getId();

        // When
        itemService.delete(itemId, userId);

        // Then
        assertThatThrownBy(() -> itemRepository.get(itemId))
            .isInstanceOf(ItemNotFoundException.class);
    }
}

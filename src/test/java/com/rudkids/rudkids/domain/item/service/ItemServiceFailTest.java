package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import com.rudkids.rudkids.domain.item.exception.ItemNotFoundException;
import com.rudkids.rudkids.domain.product.exception.ProductNotFoundException;
import com.rudkids.rudkids.domain.user.exception.NotAdminOrPartnerRoleException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ItemServiceFailTest extends ItemServiceTest {

    @Disabled("MockMultipartFile 오류 잡고 나서 테스트 코드 실행")
    @DisplayName("존재하지 않는 프로덕트에 아이템 등록시 예외가 발생한다.")
    @Test
    void 존재하지_않는_프로덕트에_아이템_등록시_예외가_발생한다() {
        // Given
        var invalidProductId = UUID.randomUUID();
        var userId = user.getId();

        // When & Then
        assertThatThrownBy(() -> itemService.create(ITEM_등록_요청, invalidProductId, userId))
            .isInstanceOf(ProductNotFoundException.class);
    }

    @DisplayName("존재하지 않는 아이템 상세 조회시 예외가 발생한다.")
    @Test
    void 존재하지_않는_아이템_상세_조회시_예외가_발생한다() {
        // Given
        var invalidItemId = UUID.randomUUID();

        // When & Then
        assertThatThrownBy(() -> itemService.find(invalidItemId))
            .isInstanceOf(ItemNotFoundException.class);
    }

    @DisplayName("존재하지 않는 아이템 업데이트 시 예외가 발생한다.")
    @Test
    void 존재하지_않는_아이템_업데이트_시_예외가_발생한다() {
        // Given
        var itemId = UUID.randomUUID();
        var userId = user.getId();

        // When & Then
        assertThatThrownBy(() -> itemService.update(아이템_수정_요청(), itemId, userId))
            .isInstanceOf(ItemNotFoundException.class);
    }

    @DisplayName("관리자나 파트너가 아닌 유저가 아이템 업데이트 시 예외가 발생한다.")
    @Test
    void 관리자나_파트너가_아닌_유저가_아이템_업데이트_시_예외가_발생한다() {
        // Given
        var itemId = item.getId();
        var userId = user.getId();
        user.changeAuthorityUser();

        // When & Then
        assertThatThrownBy(() -> itemService.update(아이템_수정_요청(), itemId, userId))
            .isInstanceOf(NotAdminOrPartnerRoleException.class);
    }

    @DisplayName("존재하지 않는 아이템 상태 변경시 예외가 발생한다.")
    @Test
    void 존재하지_않는_아이템_상태_변경시_예외가_발생한다() {
        // Given
        var invalidItemId = UUID.randomUUID();
        var status = ItemStatus.SOLD_OUT;
        var userId = user.getId();

        // When & Then
        assertThatThrownBy(() -> itemService.changeItemStatus(invalidItemId, status, userId))
            .isInstanceOf(ItemNotFoundException.class);
    }

    @DisplayName("관리자나 파트너 권한이 아닌 유저가 아이템 상태 변경시 예외가 발생한다.")
    @Test
    void 관리자나_파트너_권한이_아닌_유저가_아이템_상태_변경시_예외가_발생한다() {
        // Given
        var invalidItemId = UUID.randomUUID();
        var status = ItemStatus.SOLD_OUT;
        var userId = user.getId();
        user.changeAuthorityUser();

        // When & Then
        assertThatThrownBy(() -> itemService.changeItemStatus(invalidItemId, status, userId))
            .isInstanceOf(NotAdminOrPartnerRoleException.class);
    }

    @DisplayName("관리자나 파트너가 아닌 유저가 아이템 삭제 시 예외가 발생한다.")
    @Test
    void 관리자나_파트너가_아닌_유저가_아이템_삭제_시_예외가_발생한다() {
        // Given
        var itemId = item.getId();
        var userId = user.getId();
        user.changeAuthorityUser();

        // When & Then
        assertThatThrownBy(() -> itemService.delete(itemId, userId))
            .isInstanceOf(NotAdminOrPartnerRoleException.class);
    }
}

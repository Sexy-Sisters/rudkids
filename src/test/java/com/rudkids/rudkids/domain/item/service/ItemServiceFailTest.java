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
    @DisplayName("[아이템-생성-ProductNotFoundException]")
    @Test
    void 존재하지_않는_프로덕트에_아이템_생성_시_예외가_발생한다() {
        // Given
        var invalidProductId = UUID.randomUUID();
        var userId = user.getId();

        // When & Then
        assertThatThrownBy(() -> itemService.create(ITEM_등록_요청, invalidProductId, userId))
            .isInstanceOf(ProductNotFoundException.class);
    }

    @DisplayName("[아이템-상세조회-ItemNotFoundException]")
    @Test
    void 존재하지_않는_아이템_상세_조회시_예외가_발생한다() {
        // Given
        var invalidItemId = UUID.randomUUID();

        // When & Then
        assertThatThrownBy(() -> itemService.find(invalidItemId))
            .isInstanceOf(ItemNotFoundException.class);
    }

    @DisplayName("[아이템-수정-ItemNotFoundException] ")
    @Test
    void 존재하지_않는_아이템_수정_시_예외가_발생한다() {
        // Given
        var itemId = UUID.randomUUID();
        var userId = user.getId();

        // When & Then
        assertThatThrownBy(() -> itemService.update(아이템_수정_요청(), itemId, userId))
            .isInstanceOf(ItemNotFoundException.class);
    }

    @DisplayName("[아이템-수정-NotAdminOrPartnerRoleException]")
    @Test
    void 관리자나_파트너가_아닌_유저가_아이템_수정_시_예외가_발생한다() {
        // Given
        var itemId = item.getId();
        var userId = user.getId();
        user.changeAuthorityUser();

        // When & Then
        assertThatThrownBy(() -> itemService.update(아이템_수정_요청(), itemId, userId))
            .isInstanceOf(NotAdminOrPartnerRoleException.class);
    }

    @DisplayName("[아이템-상태변경-ItemNotFoundException]")
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

    @DisplayName("[아이템-상태변경-NotAdminOrPartnerRoleException]")
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

    @DisplayName("[아이템-삭제-NotAdminOrPartnerRoleException]")
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

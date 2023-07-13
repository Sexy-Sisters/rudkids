package com.rudkids.core.item.service;

import com.rudkids.core.common.fixtures.item.ItemServiceFixtures;
import com.rudkids.core.item.dto.ItemRequest;
import com.rudkids.core.item.exception.ItemNotFoundException;
import com.rudkids.core.product.exception.ProductNotFoundException;
import com.rudkids.core.user.exception.NotAdminOrPartnerRoleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ItemServiceFailTest extends ItemServiceFixtures {

    @DisplayName("[아이템-생성-ProductNotFoundException]")
    @Test
    void 존재하지_않는_프로덕트에_아이템_생성_시_예외가_발생한다() {
        // Given
        var invalidProductId = UUID.randomUUID();
        var userId = user.getId();

        // When & Then
        assertThatThrownBy(() -> itemService.create(userId, invalidProductId, ITEM_등록_요청))
            .isInstanceOf(ProductNotFoundException.class);
    }

    @DisplayName("[아이템-상세조회-ItemNotFoundException]")
    @Test
    void 존재하지_않는_아이템_상세_조회시_예외가_발생한다() {
        // Given
        var invalidItemName = "invalid";

        // When & Then
        assertThatThrownBy(() -> itemService.get(invalidItemName))
            .isInstanceOf(ItemNotFoundException.class);
    }

    @DisplayName("[아이템-수정-ItemNotFoundException] ")
    @Test
    void 존재하지_않는_아이템_수정_시_예외가_발생한다() {
        // Given
        var invalidItemName = "invalid";
        var userId = user.getId();

        // When & Then
        assertThatThrownBy(() -> itemService.update(userId, invalidItemName, 아이템_수정_요청()))
            .isInstanceOf(ItemNotFoundException.class);
    }

    @DisplayName("[아이템-수정-NotAdminOrPartnerRoleException]")
    @Test
    void 관리자나_파트너가_아닌_유저가_아이템_수정_시_예외가_발생한다() {
        // Given
        var invalidItemName = "invalid";
        var userId = user.getId();
        var user = userRepository.getUser(userId);
        user.changeAuthorityUser();

        // When & Then
        assertThatThrownBy(() -> itemService.update(userId, invalidItemName, 아이템_수정_요청()))
            .isInstanceOf(NotAdminOrPartnerRoleException.class);
    }

    @DisplayName("[아이템-상태변경-ItemNotFoundException]")
    @Test
    void 존재하지_않는_아이템_상태_변경시_예외가_발생한다() {
        // Given
        var invalidItemName = "invalid";
        var status = "SOLD_OUT";
        var userId = user.getId();

        // When & Then
        ItemRequest.ChangeStatus statusRequest = new ItemRequest.ChangeStatus(status);
        assertThatThrownBy(() -> itemService.changeStatus(userId, invalidItemName, statusRequest))
            .isInstanceOf(ItemNotFoundException.class);
    }

    @DisplayName("[아이템-상태변경-NotAdminOrPartnerRoleException]")
    @Test
    void 관리자나_파트너_권한이_아닌_유저가_아이템_상태_변경시_예외가_발생한다() {
        // Given
        var invalidItemName = "invalid";
        var status = "SOLD_OUT";
        var userId = user.getId();
        var user = userRepository.getUser(userId);
        user.changeAuthorityUser();

        // When & Then
        ItemRequest.ChangeStatus statusRequest = new ItemRequest.ChangeStatus(status);
        assertThatThrownBy(() -> itemService.changeStatus(userId, invalidItemName, statusRequest))
            .isInstanceOf(NotAdminOrPartnerRoleException.class);
    }

    @DisplayName("[아이템-삭제-NotAdminOrPartnerRoleException]")
    @Test
    void 관리자나_파트너가_아닌_유저가_아이템_삭제_시_예외가_발생한다() {
        // Given
        var itemName = item.getEnName();
        var userId = user.getId();
        var user = userRepository.getUser(userId);
        user.changeAuthorityUser();

        // When & Then
        assertThatThrownBy(() -> itemService.delete(userId, itemName))
            .isInstanceOf(NotAdminOrPartnerRoleException.class);
    }
}

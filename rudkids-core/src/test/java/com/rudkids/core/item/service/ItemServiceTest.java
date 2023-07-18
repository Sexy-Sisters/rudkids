package com.rudkids.core.item.service;

import com.rudkids.core.common.fixtures.item.ItemServiceFixtures;
import com.rudkids.core.item.domain.Item;
import com.rudkids.core.item.domain.ItemStatus;
import com.rudkids.core.item.domain.LimitType;
import com.rudkids.core.item.dto.ItemResponse;
import com.rudkids.core.item.exception.ItemNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ItemServiceTest extends ItemServiceFixtures {

    @Nested
    @DisplayName("아이템을 상세조회한다")
    class get {

        @Test
        @DisplayName("성공")
        void success() {
            //given

            //when
            ItemResponse.Detail findItem = itemService.get(item.getEnName());

            //then
            assertAll(
                () -> assertThat(findItem.enName()).isEqualTo("No.1"),
                () -> assertThat(findItem.price()).isEqualTo(2_990),
                () -> assertThat(findItem.quantity()).isEqualTo(1_000),
                () -> assertThat(findItem.itemBio()).isEqualTo("소개글입니다~"),
                () -> assertThat(findItem.limitType()).isEqualTo(LimitType.LIMITED),
                () -> assertThat(findItem.images()).hasSize(2)
            );
        }
    }

    @Nested
    @DisplayName("인기있는 아이템들을 조회한다")
    class getPopularItems {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            Pageable pageable = PageRequest.of(0, 5);

            //when
            var response = itemService.getPopularItems(pageable);

            //then
            assertThat(response).hasSize(1);
        }
    }
}

package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.common.fixtures.item.ItemOptionSeriesFactoryFixtures;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroup;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroupStore;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

class ItemOptionSeriesFactoryTest extends ItemOptionSeriesFactoryFixtures {

    @DisplayName("빈 리스트를 받았을 때 빈 리스트를 반환한다.")
    @Test
    void 빈_리스트를_받았을_때_빈_리스트를_반환한다() {
        List<ItemOptionGroup> itemOptionGroupList = itemOptionSeriesFactory.store(ITEM_빈_리스트_등록_요청, item);

        assertThat(itemOptionGroupList).isEmpty();
    }
}
package com.rudkids.rudkids.common.fixtures.item;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemOptionSeriesFactory;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import com.rudkids.rudkids.domain.item.domain.itemOption.ItemOptionStore;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroupStore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

@ServiceTest
public class ItemOptionSeriesFactoryFixtures {

    @Autowired
    protected ItemOptionSeriesFactory itemOptionSeriesFactory;

    protected static Item item = Item.builder().build();

    protected static ItemCommand.RegisterItemRequest ITEM_빈_리스트_등록_요청 = ItemCommand.RegisterItemRequest.builder()
        .name("Red Pill")
            .price(1_000_000)
            .quantity(1)
            .itemBio("소개글입니다~")
            .limitType(LimitType.LIMITED)
            .itemOptionGroupList(null)
            .build();

}

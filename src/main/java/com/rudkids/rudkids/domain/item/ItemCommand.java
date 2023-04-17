package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroup;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroupName;
import com.rudkids.rudkids.domain.item.domain.itemOption.ItemOption;
import com.rudkids.rudkids.domain.item.domain.itemOption.ItemOptionName;
import com.rudkids.rudkids.domain.item.domain.itemOption.ItemOptionPrice;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

public class ItemCommand {

    @Builder
    public record RegisterItemRequest(
        UUID productId,
        String name,
        String itemBio,
        int price,
        int quantity,
        LimitType limitType,
        List<RegisterItemOptionGroupRequest> itemOptionGroupList
    ) {}

    @Builder
    public record RegisterItemOptionGroupRequest(
        Integer ordering,
        String itemOptionGroupName,
        List<RegisterItemOptionRequest> itemOptionList
    ) {

        public ItemOptionGroup toEntity(Item item) {
            var itemOptionGroupName = ItemOptionGroupName.create(itemOptionGroupName());

            return ItemOptionGroup.builder()
                .item(item)
                .ordering(ordering)
                .itemOptionGroupName(itemOptionGroupName)
                .build();
        }
    }

    @Builder
    public record RegisterItemOptionRequest(
        Integer ordering,
        String itemOptionName,
        int itemOptionPrice
    ) {

        public ItemOption toEntity(ItemOptionGroup itemOptionGroup) {
            var itemOptionName = ItemOptionName.create(itemOptionName());
            var itemOptionPrice = ItemOptionPrice.create(itemOptionPrice());

            return ItemOption.builder()
                .itemOptionGroup(itemOptionGroup)
                .ordering(ordering)
                .itemOptionName(itemOptionName)
                .itemOptionPrice(itemOptionPrice)
                .build();
        }
    }
}

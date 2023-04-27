package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroup;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroupName;
import com.rudkids.rudkids.domain.item.domain.itemOption.ItemOption;
import com.rudkids.rudkids.domain.item.domain.itemOption.ItemOptionName;
import com.rudkids.rudkids.domain.item.domain.itemOption.ItemOptionPrice;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ItemCommand {

    @Builder
    public record CreateItemRequest(
        String name,
        String itemBio,
        int price,
        int quantity,
        LimitType limitType,
        List<String> images,
        List<CreateItemOptionGroupRequest> itemOptionGroupList
    ) {}

    @Builder
    public record CreateItemOptionGroupRequest(
        Integer ordering,
        String itemOptionGroupName,
        List<CreateItemOptionRequest> itemOptionList
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
    public record CreateItemOptionRequest(
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

    @Builder
    public record UpdateRequest(
        String name,
        String itemBio,
        int price,
        int quantity,
        LimitType limitType,
        List<MultipartFile> images
    ) {
    }
}

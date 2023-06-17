package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import com.rudkids.rudkids.domain.item.domain.itemOption.ItemOption;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroup;
import lombok.Builder;

import java.util.List;

public class ItemInfo {

    @Builder
    public record Detail(
        String enName,
        String koName,
        String itemBio,
        int price,
        int quantity,
        LimitType limitType,
        List<String> imageUrls,
        ItemStatus itemStatus,
        List<ItemOptionGroupInfo> itemOptionGroupInfoList
    ) {
    }

    public record ItemOptionGroupInfo(
        Integer ordering,
        String itemOptionGroupName,
        List<ItemOptionInfo> itemOptionInfoList
    ) {
        public ItemOptionGroupInfo(ItemOptionGroup itemOptionGroup, List<ItemOptionInfo> itemOptionInfoList) {
            this(
                itemOptionGroup.getOrdering(),
                itemOptionGroup.getItemOptionGroupName(),
                itemOptionInfoList
            );
        }
    }

    public record ItemOptionInfo(
        Integer ordering,
        String itemOptionName,
        Integer itemOptionPrice
    ) {
        public ItemOptionInfo(ItemOption itemOption) {
            this(
                itemOption.getOrdering(),
                itemOption.getItemOptionName(),
                itemOption.getItemOptionPrice()
            );
        }
    }
}

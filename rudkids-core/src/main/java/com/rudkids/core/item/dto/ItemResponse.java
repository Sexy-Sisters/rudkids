package com.rudkids.core.item.dto;

import com.rudkids.core.item.domain.Item;
import com.rudkids.core.item.domain.ItemStatus;
import com.rudkids.core.item.domain.LimitType;
import com.rudkids.core.item.domain.itemOption.ItemOption;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class ItemResponse {

    @Builder
    public record Main(
        UUID itemId,
        String name,
        int price,
        List<String> imageUrls,
        ItemStatus itemStatus
    ) {
        public Main(Item item) {
            this(item.getId(), item.getEnName(), item.getPrice(), item.getImageUrls(), item.getItemStatus());
        }
    }

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
        List<DetailOptionGroup> itemOptionGroupInfoList
    ) {
        public Detail(Item item) {
            this(
                item.getEnName(),
                item.getKoName(),
                item.getItemBio(),
                item.getPrice(),
                item.getQuantity(),
                item.getLimitType(),
                item.getImageUrls(),
                item.getItemStatus(),
                getOptionGroup(item)
            );
        }

        private static List<DetailOptionGroup> getOptionGroup(Item item) {
            return item.getItemOptionGroups().stream()
                .map(group -> group.getItemOptions().stream()
                    .map(DetailOption::new)
                    .collect(collectingAndThen(toList(), options
                        -> new DetailOptionGroup(group.getOrdering(), group.getItemOptionGroupName(), options))))
                .toList();
        }
    }

    public record DetailOptionGroup(
        Integer ordering,
        String itemOptionGroupName,
        List<DetailOption> itemOptionInfoList
    ) {}

    public record DetailOption(
        Integer ordering,
        String itemOptionName,
        Integer itemOptionPrice
    ) {
        public DetailOption(ItemOption option) {
            this(option.getOrdering(), option.getItemOptionName(), option.getItemOptionPrice());
        }
    }
}
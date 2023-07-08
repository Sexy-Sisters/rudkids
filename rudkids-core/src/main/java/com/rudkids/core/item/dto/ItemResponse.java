package com.rudkids.core.item.dto;

import com.rudkids.core.image.dto.ImageResponse;
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
        List<ImageResponse.Info> images,
        ItemStatus itemStatus,
        List<DetailOptionGroup> itemOptionGroupInfoList,
        String videoUrl
    ) {
        public Detail(Item item) {
            this(
                item.getEnName(),
                item.getKoName(),
                item.getItemBio(),
                item.getPrice(),
                item.getQuantity(),
                item.getLimitType(),
                item.getImages().stream()
                    .map(ImageResponse.Info::new)
                    .toList(),
                item.getItemStatus(),
                getOptionGroup(item),
                item.getVideoUrl()
            );
        }

        private static List<DetailOptionGroup> getOptionGroup(Item item) {
            return item.getItemOptionGroups().stream()
                .map(group -> group.getItemOptions().stream()
                    .map(DetailOption::new)
                    .collect(collectingAndThen(toList(), options
                        -> new DetailOptionGroup(group.getItemOptionGroupName(), options))))
                .toList();
        }
    }

    public record DetailOptionGroup(
        String itemOptionGroupName,
        List<DetailOption> itemOptionInfoList
    ) {
    }

    public record DetailOption(
        String itemOptionName,
        Integer itemOptionPrice
    ) {
        public DetailOption(ItemOption option) {
            this(option.getItemOptionName(), option.getItemOptionPrice());
        }
    }

    public record VideoImage(String name, String imageUrl) {
        public VideoImage(Item item) {
            this(item.getEnName(), item.getVideoImageUrl());
        }
    }

    public record Video(String name, String videoUrl) {
        public Video(Item item) {
            this(item.getEnName(), item.getVideoUrl());
        }
    }
}

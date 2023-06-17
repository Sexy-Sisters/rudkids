package com.rudkids.rudkids.interfaces.item.dto;

import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.interfaces.image.dto.ImageRequest;
import lombok.Builder;

import java.util.List;

public class ItemRequest {

    @Builder
    public record RegisterItem(
        String enName,
        String koName,
        String itemBio,
        int price,
        int quantity,
        LimitType limitType,
        List<ImageRequest> images,
        List<ItemRequest.RegisterItemOptionGroup> itemOptionGroupList
    ) {
    }

    @Builder
    public record RegisterItemOptionGroup(
        Integer ordering,
        String itemOptionGroupName,
        List<ItemRequest.RegisterItemOption> itemOptionList
    ) {}

    @Builder
    public record RegisterItemOption(
        Integer ordering,
        String itemOptionName,
        int itemOptionPrice
    ) {}

    @Builder
    public record Update(
        String enName,
        String koName,
        String itemBio,
        int price,
        int quantity,
        LimitType limitType,
        List<ImageRequest> images
    ) {
    }

    public record ChangeStatus(ItemStatus itemStatus) {
    }
}

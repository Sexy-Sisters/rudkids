package com.rudkids.core.item.dto;

import com.rudkids.core.image.dto.ImageRequest;
import com.rudkids.core.item.domain.LimitType;
import lombok.Builder;

import java.util.List;

public class ItemRequest {

    @Builder
    public record Create(
        String enName,
        String koName,
        String itemBio,
        int price,
        int quantity,
        LimitType limitType,
        List<ImageRequest.Create> images,
        List<CreateItemOptionGroup> itemOptionGroupList,
        ImageRequest.Create videoImage,
        String videoUrl
    ) {}

    @Builder
    public record CreateItemOptionGroup(
        Integer ordering,
        String itemOptionGroupName,
        List<CreateItemOption> itemOptionList
    ) {
    }

    @Builder
    public record CreateItemOption(
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
        List<ImageRequest.Create> images
    ) {}

    @Builder
    public record ChangeStatus(String itemStatus) {}
}

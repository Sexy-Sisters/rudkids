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
        List<CreateImage> images,
        List<CreateItemOptionGroup> itemOptionGroupList,
        ImageRequest.Create videoImage,
        String videoUrl
    ) {}

    public record CreateImage(
        ImageRequest.Create image,
        int ordering
    ) {}

    @Builder
    public record CreateItemOptionGroup(
        String itemOptionGroupName,
        List<CreateItemOption> itemOptionList,
        int ordering
    ) {
    }

    @Builder
    public record CreateItemOption(
        String itemOptionName,
        int itemOptionPrice,
        int ordering
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

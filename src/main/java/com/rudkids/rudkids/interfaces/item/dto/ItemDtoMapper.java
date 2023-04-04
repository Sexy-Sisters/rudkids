package com.rudkids.rudkids.interfaces.item.dto;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemInfo;
import org.springframework.stereotype.Component;

@Component
public class ItemDtoMapper {

    public ItemCommand.RegisterRequest to(ItemRequest.Register request) {
        return ItemCommand.RegisterRequest.builder()
            .productId(request.productId())
            .name(request.name())
            .price(request.price())
            .quantity(request.quantity())
            .limitType(request.limitType())
            .build();
    }

    public ItemResponse.Main to(ItemInfo.Main info) {
        return ItemResponse.Main.builder()
            .name(info.getName())
            .price(info.getPrice())
            .itemStatus(info.getItemStatus())
            .build();
    }

    public ItemResponse.Detail to(ItemInfo.Detail info) {
        return ItemResponse.Detail.builder()
            .name(info.getName())
            .price(info.getPrice())
            .bio(info.getBio())
            .quantity(info.getQuantity())
            .limitType(info.getLimitType())
            .itemStatus(info.getItemStatus())
            .build();
    }
}

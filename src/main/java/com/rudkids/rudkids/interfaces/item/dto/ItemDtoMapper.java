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
            .itemBio(request.itemBio())
            .price(request.price())
            .quantity(request.quantity())
            .limitType(request.limitType())
            .build();
    }

    public ItemResponse.Main to(ItemInfo.Main info) {
        return ItemResponse.Main.builder()
            .id(info.id())
            .name(info.name())
            .price(info.price())
            .itemStatus(info.itemStatus())
            .build();
    }

    public ItemResponse.Detail to(ItemInfo.Detail info) {
        return ItemResponse.Detail.builder()
            .name(info.name())
            .price(info.price())
            .itemBio(info.itemBio())
            .quantity(info.quantity())
            .limitType(info.limitType())
            .itemStatus(info.itemStatus())
            .build();
    }
}

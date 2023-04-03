package com.rudkids.rudkids.interfaces.item.dto;

import com.rudkids.rudkids.domain.item.ItemCommand;
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
}

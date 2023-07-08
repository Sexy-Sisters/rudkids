package com.rudkids.core.cart.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

public class CartRequest {

    @Builder
    public record AddCartItem(
        String itemName,
        int amount,
        List<AddCartItemOptionGroup> optionGroups
    ) {}

    @Builder
    public record AddCartItemOptionGroup(String name, AddCartItemOption option) { }

    public record AddCartItemOption(String name, int price) { }

    @Builder
    public record UpdateCartItemAmount(UUID cartItemId, int amount) {}

    public record SelectCartItem(List<UUID> ids) {}
}
package com.rudkids.core.cart.infrastructure;

import com.rudkids.core.cart.domain.*;
import com.rudkids.core.cart.dto.CartRequest;
import com.rudkids.core.cart.exception.CartItemNotFoundException;
import com.rudkids.core.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartItemRepositoryImpl implements CartItemRepository {
    private final JpaCartItemRepository cartItemRepository;

    @Override
    public CartItem getOrCreate(Cart cart, Item item, CartRequest.AddCartItem request) {
        if(cartItemRepository.findByCartAndItem(cart, item).isPresent()) {
            var cartItem = cartItemRepository.findByCartAndItem(cart, item).get();
            List<String> cartItemOptionNames = cartItem.getCartItemOptionGroups().stream()
                .map(CartItemOptionGroup::getOptionName)
                .toList();

            List<String> targetOptionNames = toCartItemOptionGroup(request.optionGroups()).stream()
                .map(CartItemOptionGroup::getOptionName)
                .toList();

            if (isSameOptions(cartItemOptionNames, targetOptionNames)) {
                cartItem.addAmount(request.amount());
                return cartItem;
            }
        }

        return createCartItem(cart, item, request);
    }

    private boolean isSameOptions(List<String> options, List<String> targetOptions) {
        return options.size() == targetOptions.size()
            && options.containsAll(targetOptions);
    }

    private List<CartItemOptionGroup> toCartItemOptionGroup(List<CartRequest.AddCartItemOptionGroup> groups) {
        return groups.stream()
            .map(this::toCartItemOption)
            .toList();
    }

    private CartItemOptionGroup toCartItemOption(CartRequest.AddCartItemOptionGroup option) {
        return CartItemOptionGroup.builder()
            .name(option.name())
            .cartItemOption(option.toCartItemOption())
            .build();
    }

    private CartItem createCartItem(Cart cart, Item item, CartRequest.AddCartItem request) {
        var cartItem = CartItem.builder()
            .cart(cart)
            .item(item)
            .price(item.getPrice())
            .amount(request.amount())
            .imageUrl(item.getCartItemImageUrl())
            .build();
        cart.addCartItem(cartItem);

        generateChildEntities(cartItem, request);
        return cartItemRepository.save(cartItem);
    }

    private void generateChildEntities(CartItem cartItem, CartRequest.AddCartItem request) {
        request.optionGroups().forEach(group -> {
            var cartItemOptionGroup = CartItemOptionGroup.builder()
                .cartItem(cartItem)
                .name(group.name())
                .cartItemOption(group.toCartItemOption())
                .build();

            cartItem.addCartItemOptionGroup(cartItemOptionGroup);
        });
    }

    @Override
    public CartItem get(UUID id) {
        return cartItemRepository.findById(id)
            .orElseThrow(CartItemNotFoundException::new);
    }

    @Override
    public void delete(List<UUID> ids) {
        cartItemRepository.deleteAllByIds(ids);
    }
}

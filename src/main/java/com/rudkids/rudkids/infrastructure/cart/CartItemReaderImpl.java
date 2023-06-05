package com.rudkids.rudkids.infrastructure.cart;

import com.rudkids.rudkids.domain.cart.CartCommand;
import com.rudkids.rudkids.domain.cart.CartItemReader;
import com.rudkids.rudkids.domain.cart.CartItemSeriesFactory;
import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.cart.domain.CartItemOptionGroup;
import com.rudkids.rudkids.domain.cart.exception.CartItemNotFoundException;
import com.rudkids.rudkids.domain.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartItemReaderImpl implements CartItemReader {
    private final CartItemRepository cartItemRepository;
    private final CartItemSeriesFactory cartItemSeriesFactory;

    @Override
    public CartItem getCartItem(UUID cartItemId) {
        return cartItemRepository.findById(cartItemId)
            .orElseThrow(CartItemNotFoundException::new);
    }

    @Override
    public CartItem getCartItemOrCreate(Cart cart, Item item, CartCommand.AddCartItem command) {
        if (cartItemRepository.findByCartAndItem(cart, item).isPresent()) {
            CartItem cartItem = cartItemRepository.findByCartAndItem(cart, item).get();
            List<String> cartItemOptionNames = cartItem.getCartItemOptionGroups().stream()
                .map(CartItemOptionGroup::getOptionName)
                .toList();

            List<String> targetOptionNames = toCartItemOptionGroup(command.optionGroups()).stream()
                .map(CartItemOptionGroup::getOptionName)
                .toList();

            if (isSameOptions(cartItemOptionNames, targetOptionNames)) {
                cartItem.addAmount(command.amount());
                return cartItem;
            }
        }
        return createCartItem(cart, item, command);
    }

    private boolean isSameOptions(List<String> options, List<String> targetOptions) {
        return options.size() == targetOptions.size()
            && options.containsAll(targetOptions);
    }

    private List<CartItemOptionGroup> toCartItemOptionGroup(List<CartCommand.AddCartItemOptionGroup> groups) {
        return groups.stream()
            .map(this::toCartItemOption)
            .toList();
    }

    private CartItemOptionGroup toCartItemOption(CartCommand.AddCartItemOptionGroup option) {
        return CartItemOptionGroup.builder()
            .name(option.name())
            .cartItemOption(option.toCartItemOption())
            .build();
    }

    private CartItem createCartItem(Cart cart, Item item, CartCommand.AddCartItem command) {
        var cartItem = CartItem.builder()
            .cart(cart)
            .item(item)
            .price(item.getPrice())
            .amount(command.amount())
            .imageUrl(item.getCartItemImageUrl())
            .build();
        cart.addCartItem(cartItem);

        cartItemSeriesFactory.store(cartItem, command);
        return cartItemRepository.save(cartItem);
    }
}

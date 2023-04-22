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
    public CartItem getCartItem(Cart cart, Item item, CartCommand.AddCartItem command) {
        if(cartItemRepository.findByCartAndItem(cart, item).isPresent()) {
            CartItem cartItem = cartItemRepository.findByCartAndItem(cart, item).get();

            /*
            2023.4.21 nswon
            두 개의 장바구니아이템 옵션값이 같은지 비교하는 로직을 query문으로 변경해야 한다.
             */
            List<String> cartItemOptionNames = cartItem.getCartItemOptionGroups().stream()
                    .map(CartItemOptionGroup::getCartItemOption)
                    .toList();

            List<String> targetOptionNames = toCartItemOptionGroup(command.optionGroups()).stream()
                    .map(CartItemOptionGroup::getCartItemOption)
                    .toList();

            if(cartItemOptionNames.size() == targetOptionNames.size()
                    && cartItemOptionNames.containsAll(targetOptionNames)) {
                return cartItem;
            }
        }
        return createCartItem(cart, item, command);
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
                .amount(command.amount())
                .price(item.getPrice())
                .build();
        cart.addCartItem(cartItem);

        cartItemSeriesFactory.store(cartItem, command.optionGroups());
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem getCartItem(UUID cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(CartItemNotFoundException::new);
    }
}

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
    private final CartItemNameGenerator cartItemNameGenerator;

    @Override
    public CartItem getOrCreate(Cart cart, Item item, CartRequest.AddCartItem request) {
        String name = cartItemNameGenerator.generate(item.getEnName(), request);

        return cartItemRepository.findByCartAndItem(cart, item)
            .filter(cartItem -> cartItem.isSameName(name))
            .findFirst()
            .map(cartItem -> {
                cartItem.addAmount(request.amount());
                return cartItem;
            })
            .orElseGet(() -> createCartItem(cart, item, name, request));
    }

    private CartItem createCartItem(Cart cart, Item item, String name, CartRequest.AddCartItem request) {
        int price = item.getPrice() + sumCartItemTotalPrice(request);

        var cartItem = CartItem.builder()
            .cart(cart)
            .item(item)
            .name(name)
            .imageUrl(item.getFirstImageUrl())
            .amount(request.amount())
            .price(price)
            .build();
        cart.addCartItem(cartItem);

        return cartItemRepository.save(cartItem);
    }

    private int sumCartItemTotalPrice(CartRequest.AddCartItem request) {
        return request.optionGroups().stream()
            .mapToInt(it -> it.option().price())
            .sum();
    }

    @Override
    public CartItem get(UUID id) {
        return cartItemRepository.findById(id)
            .orElseThrow(CartItemNotFoundException::new);
    }

    @Override
    public void selects(List<UUID> ids) {
        cartItemRepository.updateSelects(ids);
    }

    @Override
    public void deleteSelected() {
        var cartItems = cartItemRepository.findAll().stream()
            .filter(CartItem::isSelected)
            .toList();

        cartItemRepository.deleteAllInBatch(cartItems);
    }

    @Override
    public void deleteSoldOutCartItems() {
        var cartItems = cartItemRepository.findAll().stream()
            .filter(CartItem::isSoldOut)
            .toList();

        cartItemRepository.deleteAllInBatch(cartItems);
    }

    @Override
    public void delete(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }
}

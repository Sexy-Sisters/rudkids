package com.rudkids.rudkids.domain.cart.service;

import com.rudkids.rudkids.domain.cart.*;
import com.rudkids.rudkids.domain.item.ItemReader;
import com.rudkids.rudkids.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {
    private final UserReader userReader;
    private final ItemReader itemReader;
    private final CartReader cartReader;
    private final CartItemReader cartItemReader;
    private final CartItemStore cartItemStore;
    private final CartItemMapper cartItemMapper;

    @Override
    public UUID addCartItem(UUID userId, CartCommand.AddCartItem command) {
        var user = userReader.getUser(userId);
        var cart = cartReader.getCartOrCreate(user);
        var item = itemReader.getItem(command.itemId());
        var cartItem = cartItemReader.getCartItem(cart, item, command);
        cart.addCartItemCount(command.amount());
        return cartItem.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public CartInfo.Main findCartItems(UUID userId) {
        var user = userReader.getUser(userId);
        var cart = cartReader.getCartOrCreate(user);

        int totalCartItemPrice = cart.getTotalCartItemPrice();
        return cart.getCartItems().stream()
                .map(cartItemMapper::toInfo)
                .collect(collectingAndThen(toList(), cartItems -> new CartInfo.Main(totalCartItemPrice, cartItems)));
    }

    @Override
    public void updateCartItemAmount(UUID userId, CartCommand.UpdateCartItemAmount command) {
        var user = userReader.getUser(userId);
        var cart = cartReader.getCart(command.cartId());
        cart.validateHasSameUser(user);

        var cartItem = cartItemReader.getCartItem(command.cartItemId());
        cart.updateCartItemCount(cartItem.getAmount(), command.amount());
        cartItem.updateAmount(command.amount());
    }

    @Override
    public void deleteCartItems(UUID userId, CartCommand.DeleteCartItems command) {
        var user = userReader.getUser(userId);
        var cart = cartReader.getCart(command.cartId());
        cart.validateHasSameUser(user);

        cartItemStore.delete(command.cartItemIds());
    }
}

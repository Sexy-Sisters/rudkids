package com.rudkids.rudkids.domain.cart.application;

import com.rudkids.rudkids.domain.item.ItemReader;
import com.rudkids.rudkids.domain.user.application.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {
    private final UserReader userReader;
    private final ItemReader itemReader;
    private final CartReader cartReader;
    private final CartItemReader cartItemReader;
    private final CartItemMapper cartItemMapper;

    @Override
    public void addCartItem(UUID userId, CartCommand.AddCartItem command) {
        var user = userReader.getUser(userId);
        var cart = cartReader.getCart(user);
        var item = itemReader.getItem(command.itemId());
        var cartItem = cartItemReader.getCartItem(cart, item, command.amount());
        cart.addCartItemCount(cartItem.getAmount());
    }

    @Transactional(readOnly = true)
    @Override
    public CartInfo.Main findCartItems(UUID userId) {
        var user = userReader.getUser(userId);
        var cart = cartReader.getCart(user);
        int totalCartItemPrice = cart.getTotalCartItemPrice();

        List<CartItemInfo.Main> cartItems = cart.getCartItems().stream()
                .map(cartItemMapper::toMain)
                .toList();

        return CartInfo.Main.builder()
                .totalCartItemPrice(totalCartItemPrice)
                .cartItems(cartItems)
                .build();
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
}

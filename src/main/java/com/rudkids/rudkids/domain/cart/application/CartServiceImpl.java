package com.rudkids.rudkids.domain.cart.application;

import com.rudkids.rudkids.domain.item.ItemReader;
import com.rudkids.rudkids.domain.user.application.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {
    private final UserReader userReader;
    private final ItemReader itemReader;
    private final CartReader cartReader;
    private final CartItemReader cartItemReader;

    @Override
    public void addCartItem(UUID id, CartCommand.AddCartItem command) {
        var user = userReader.getUser(id);
        var cart = cartReader.getCart(user);
        var item = itemReader.getItem(command.itemId());
        var cartItem = cartItemReader.getCartItem(cart, item);

        int cartItemAmount = command.amount();
        cartItem.addAmount(cartItemAmount);
        cart.addCartItemCount(cartItemAmount);
    }
}

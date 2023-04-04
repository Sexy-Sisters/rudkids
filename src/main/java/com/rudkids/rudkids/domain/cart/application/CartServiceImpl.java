package com.rudkids.rudkids.domain.cart.application;

import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.item.ItemReader;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.user.application.UserReader;
import com.rudkids.rudkids.domain.user.domain.User;
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
    private final CartItemStore cartItemStore;
    private final CartStore cartStore;

    @Override
    public void addCartItem(UUID id, CartCommand.AddCartItem command) {
        var findUser = userReader.getUser(id);
        var findCart = findCart(findUser);
        var findItem = itemReader.getItem(command.itemId());

        var cartItem = findCartItem(findCart, findItem);
        cartItem.addAmount(command.amount());

        findCart.addCartItemCount(command.amount());
    }

    private Cart findCart(User user) {
        return cartReader.findCart(user.getId())
                .orElseGet(() -> createCart(user));
    }

    private Cart createCart(User user) {
        var cart = Cart.create(user);
        return cartStore.store(cart);
    }

    private CartItem findCartItem(Cart cart, Item item) {
        return cartItemReader.findCartItem(cart, item)
                .orElseGet(() -> createCartItem(cart, item));
    }

    private CartItem createCartItem(Cart cart, Item item) {
        var cartItem = CartItem.builder()
                .cart(cart)
                .item(item)
                .build();

        cart.addCartItem(cartItem);
        return cartItemStore.store(cartItem);
    }
}

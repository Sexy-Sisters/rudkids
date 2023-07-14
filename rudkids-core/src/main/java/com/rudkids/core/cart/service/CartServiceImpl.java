package com.rudkids.core.cart.service;

import com.rudkids.core.cart.domain.CartItem;
import com.rudkids.core.cart.domain.CartItemRepository;
import com.rudkids.core.cart.domain.CartRepository;
import com.rudkids.core.cart.dto.CartItemResponse;
import com.rudkids.core.cart.dto.CartRequest;
import com.rudkids.core.cart.dto.CartResponse;
import com.rudkids.core.item.domain.ItemRepository;
import com.rudkids.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public UUID addCartItem(UUID userId, CartRequest.AddCartItem request) {
        var user = userRepository.getUser(userId);
        var cart = cartRepository.getOrCreate(user);
        var item = itemRepository.getByEnNme(request.itemName());

        var cartItem = cartItemRepository.getOrCreate(cart, item, request);
        return cartItem.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public CartResponse.Main getCartItems(UUID userId) {
        var user = userRepository.getUser(userId);
        var cart = cartRepository.getOrCreate(user);
        int totalCartItemPrice = cart.calculateTotalPrice();

        return cart.getCartItems().stream()
            .map(CartItemResponse.Main::new)
            .collect(collectingAndThen(toList(), cartItems ->
                new CartResponse.Main(totalCartItemPrice, cartItems)));
    }

    @Transactional(readOnly = true)
    @Override
    public CartResponse.Select getSelected(UUID userId) {
        var user = userRepository.getUser(userId);
        var cart = cartRepository.get(user);
        return cart.getCartItems().stream()
            .filter(CartItem::isSelectTrue)
            .map(CartItemResponse.Select::new)
            .collect(collectingAndThen(toList(), cartItems ->
                new CartResponse.Select(cart.calculateTotalPrice(), cartItems)));
    }

    @Override
    public void updateCartItemAmount(UUID userId, CartRequest.UpdateCartItemAmount request) {
        var user = userRepository.getUser(userId);
        var cart = cartRepository.get(user);
        cart.validateHasSameUser(user);

        var cartItem = cartItemRepository.get(request.cartItemId());
        cartItem.updateAmount(request.amount());
    }

    @Override
    public void select(UUID userId, CartRequest.SelectCartItem request) {
        var user = userRepository.getUser(userId);
        var cart = cartRepository.get(user);
        cart.validateHasSameUser(user);
        cart.initSelect();

        cartItemRepository.selects(request.ids());
    }

    @Override
    public void deleteCartItem(UUID userId, UUID cartItemId) {
        var user = userRepository.getUser(userId);
        var cart = cartRepository.get(user);
        cart.validateHasSameUser(user);

        var cartItem = cartItemRepository.get(cartItemId);
        cartItemRepository.delete(cartItem);
    }
}

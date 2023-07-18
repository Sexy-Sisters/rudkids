package com.rudkids.core.cart.service;

import com.rudkids.core.cart.domain.CartItemRepository;
import com.rudkids.core.cart.domain.CartRepository;
import com.rudkids.core.cart.dto.CartItemResponse;
import com.rudkids.core.cart.dto.CartRequest;
import com.rudkids.core.cart.dto.CartResponse;
import com.rudkids.core.item.domain.ItemRepository;
import com.rudkids.core.order.infrastructure.OrderNameGenerator;
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
public class CartService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderNameGenerator orderNameGenerator;

    public UUID addCartItem(UUID userId, CartRequest.AddCartItem request) {
        var user = userRepository.getUser(userId);
        var cart = cartRepository.getOrCreate(user);
        var item = itemRepository.getByEnNme(request.itemName());

        var cartItem = cartItemRepository.getOrCreate(cart, item, request);
        return cartItem.getId();
    }

    @Transactional(readOnly = true)
    public List<CartItemResponse.Main> getCartItems(UUID userId) {
        var user = userRepository.getUser(userId);
        var cart = cartRepository.getOrCreate(user);

        return cart.getCartItems().stream()
            .map(CartItemResponse.Main::new)
            .toList();
    }

    @Transactional(readOnly = true)
    public CartResponse.Select getSelected(UUID userId) {
        var user = userRepository.getUser(userId);
        var cart = cartRepository.get(user);
        return cart.getSelectedCartItems().stream()
            .map(CartItemResponse.Select::new)
            .collect(collectingAndThen(toList(), cartItems ->
                new CartResponse.Select(cart.calculateSelectedCartItemsTotalPrice(), orderNameGenerator.generate(cartItems), cartItems)));
    }

    public void updateCartItemAmount(UUID userId, CartRequest.UpdateCartItemAmount request) {
        var user = userRepository.getUser(userId);
        var cart = cartRepository.get(user);
        cart.validateHasSameUser(user);

        var cartItem = cartItemRepository.get(request.cartItemId());
        cartItem.updateAmount(request.amount());
    }

    public void select(UUID userId, CartRequest.SelectCartItem request) {
        var user = userRepository.getUser(userId);
        var cart = cartRepository.get(user);
        cart.validateHasSameUser(user);
        cart.initSelect();

        cartItemRepository.selects(request.ids());
    }

    public void deleteCartItem(UUID userId, UUID cartItemId) {
        var user = userRepository.getUser(userId);
        var cart = cartRepository.get(user);
        cart.validateHasSameUser(user);

        var cartItem = cartItemRepository.get(cartItemId);
        cartItemRepository.delete(cartItem);
    }
}

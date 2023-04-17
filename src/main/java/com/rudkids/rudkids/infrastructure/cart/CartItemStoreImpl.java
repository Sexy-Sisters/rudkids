package com.rudkids.rudkids.infrastructure.cart;

import com.rudkids.rudkids.domain.cart.CartItemStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartItemStoreImpl implements CartItemStore {
    private final CartItemRepository cartItemRepository;

    @Override
    public void delete(List<UUID> cartItemIds) {
        cartItemRepository.deleteAllByIds(cartItemIds);
    }
}

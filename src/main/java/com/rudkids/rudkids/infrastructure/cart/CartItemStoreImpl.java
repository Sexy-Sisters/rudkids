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
    private final CartItemOptionGroupRepository cartItemOptionGroupRepository;

    @Override
    public void delete(List<UUID> cartItemIds) {
        cartItemOptionGroupRepository.deleteAllInBatch();
        cartItemRepository.deleteAllByIds(cartItemIds);
    }
}

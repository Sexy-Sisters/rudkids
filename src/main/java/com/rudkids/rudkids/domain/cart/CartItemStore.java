package com.rudkids.rudkids.domain.cart;

import java.util.List;
import java.util.UUID;

public interface CartItemStore {
    void delete(List<UUID> cartItemIds);
}

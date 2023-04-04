package com.rudkids.rudkids.domain.cart.repository;

import com.rudkids.rudkids.domain.cart.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
}

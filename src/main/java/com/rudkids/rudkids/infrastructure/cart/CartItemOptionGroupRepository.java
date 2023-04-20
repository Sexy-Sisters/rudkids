package com.rudkids.rudkids.infrastructure.cart;

import com.rudkids.rudkids.domain.cart.domain.CartItemOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartItemOptionGroupRepository extends JpaRepository<CartItemOptionGroup, UUID> {
}

package com.rudkids.rudkids.domain.cart.repository;

import com.rudkids.rudkids.domain.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByUserId(UUID id);
}

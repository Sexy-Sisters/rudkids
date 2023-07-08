package com.rudkids.core.cart.infrastructure;

import com.rudkids.core.cart.domain.Cart;
import com.rudkids.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaCartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByUser(User user);
}
package com.rudkids.rudkids.infrastructure.cart;

import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    Optional<CartItem> findByCartAndItem(Cart cart, Item item);

    @Modifying(clearAutomatically = true)
    @Query(value = "delete from CartItem c where c.id in :ids")
    void deleteAllByIds(@Param("ids") List<UUID> ids);
}

package com.rudkids.core.cart.infrastructure;

import com.rudkids.core.cart.domain.Cart;
import com.rudkids.core.cart.domain.CartItem;
import com.rudkids.core.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaCartItemRepository extends JpaRepository<CartItem, UUID> {
    Optional<CartItem> findByCartAndItem(Cart cart, Item item);

    @Modifying(clearAutomatically = true)
    @Query(value = "delete from CartItem c where c.id in :ids")
    void deleteAllByIds(@Param("ids") List<UUID> ids);
}

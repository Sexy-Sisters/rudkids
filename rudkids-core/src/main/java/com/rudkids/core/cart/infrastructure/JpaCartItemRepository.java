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
import java.util.stream.Stream;

public interface JpaCartItemRepository extends JpaRepository<CartItem, UUID> {
    Stream<CartItem> findByCartAndItem(Cart cart, Item item);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE CartItem c SET c.selected = true WHERE c.id IN :ids")
    void updateSelects(@Param("ids") List<UUID> ids);
}

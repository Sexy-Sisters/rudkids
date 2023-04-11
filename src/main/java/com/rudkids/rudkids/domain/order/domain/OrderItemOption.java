package com.rudkids.rudkids.domain.order.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_order_item_option")
public class OrderItemOption {

    @Id
    @Column(name = "order_item_option_id")
    private final UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @JoinColumn(name = "order_item_option_group_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderItemOptionGroup orderItemOptionGroup;
}

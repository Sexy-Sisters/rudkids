package com.rudkids.rudkids.domain.order.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.common.AbstractEntity;
import com.rudkids.rudkids.domain.user.domain.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_order")
public class Order extends AbstractEntity {

    @Id
    @Column(name = "order_id", columnDefinition = "BINARY(16)")
    private final UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    protected Order() {
    }

    public Order(User user) {
        this.user = user;
    }

    public void setRecipient(User user) {
        user.order(this);
    }
}

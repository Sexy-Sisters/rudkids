package com.rudkids.rudkids.domain.order.domain;

import com.rudkids.rudkids.common.AbstractEntity;
import com.rudkids.rudkids.domain.order.domain.orderItem.OrderItem;
import com.rudkids.rudkids.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_order")
public class Order extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "order_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Embedded
    private DeliveryFragment deliveryFragment;

    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public int calculateTotalAmount() {
        return orderItems.stream()
            .mapToInt(OrderItem::calculateTotalAmount)
            .sum();
    }

    @Builder
    public Order(User user, PayMethod payMethod, DeliveryFragment deliveryFragment) {
        this.user = user;
        this.payMethod = payMethod;
        this.deliveryFragment = deliveryFragment;
        this.orderStatus = OrderStatus.INIT;
    }

    public void setRecipient(User user) {
        user.getOrders().add(this);
        this.user = user;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public UUID getId() {
        return id;
    }
}

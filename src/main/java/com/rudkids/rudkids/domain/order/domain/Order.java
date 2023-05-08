package com.rudkids.rudkids.domain.order.domain;

import com.rudkids.rudkids.common.AbstractEntity;
import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.order.exception.OrderAlreadyPaidException;
import com.rudkids.rudkids.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_order")
public class Order extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "order_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Embedded
    private DeliveryFragment deliveryFragment;

    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder
    public Order(Cart cart, PayMethod payMethod, DeliveryFragment deliveryFragment) {
        this.cart = cart;
        this.payMethod = payMethod;
        this.deliveryFragment = deliveryFragment;
        this.orderStatus = OrderStatus.INIT;
    }

    public void startDelivery() {
        this.orderStatus = OrderStatus.IN_DELIVERY;
    }

    public void prepareDelivery() {
        this.orderStatus = OrderStatus.DELIVERY_PREPARE;
    }

    public void completeDelivery() {
        this.orderStatus = OrderStatus.DELIVERY_COMPLETE;
    }

    public void completeOrder() {
        this.orderStatus = OrderStatus.ORDER_COMPLETE;
    }

    public void addUser(User user) {
        user.getOrders().add(this);
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }

    public DeliveryFragment getDeliveryFragment() {
        return deliveryFragment;
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

    public void updateDeliveryFragment(String receiverName,
                                       String receiverPhone,
                                       String receiverZipcode,
                                       String receiverAddress1,
                                       String receiverAddress2,
                                       String etcMessage) {
        deliveryFragment.update(
            receiverName,
            receiverPhone,
            receiverZipcode,
            receiverAddress1,
            receiverAddress2,
            etcMessage
        );
    }
    public void validateNotPaid() {
        if(OrderStatus.INIT != orderStatus) {
            throw new OrderAlreadyPaidException();
        }
    }

    public void activateCart() {
        this.cart.activate();
    }
}

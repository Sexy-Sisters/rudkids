package com.rudkids.core.order.domain;

import com.rudkids.core.cart.domain.Cart;
import com.rudkids.core.common.domain.AbstractEntity;
import com.rudkids.core.delivery.domain.Delivery;
import com.rudkids.core.order.exception.OrderAlreadyPaidException;
import com.rudkids.core.payment.exception.InvalidAmountException;
import com.rudkids.core.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Entity
@Table(name = "tbl_order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.INIT;

    private Order(User user, Cart cart, Delivery delivery, PayMethod payMethod) {
        this.user = user;
        user.registerOrder(this);
        this.cart = cart;
        this.delivery = delivery;
        this.payMethod = payMethod;
    }

    public static Order create(User user, Cart cart, Delivery delivery, PayMethod payMethod) {
        return new Order(user, cart, delivery, payMethod);
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

    public void validateNotPaid() {
        if(OrderStatus.INIT != orderStatus) {
            throw new OrderAlreadyPaidException();
        }
    }

    public void changeStatus(OrderStatus status) {
        this.orderStatus = status;
    }

    public void activateCart() {
        this.cart.activate();
    }

    public void changeOrderComplete() {
        this.orderStatus = OrderStatus.ORDER_COMPLETE;
    }

    public void validateAmount(int amount) {
        if(cart.calculateTotalPrice() != amount) {
            throw new InvalidAmountException();
        }
    }
}

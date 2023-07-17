package com.rudkids.core.order.domain;

import com.rudkids.core.common.domain.AbstractEntity;
import com.rudkids.core.delivery.exception.DeliveryAlreadyCompletedException;
import com.rudkids.core.order.exception.InvalidAmountException;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.exception.DifferentUserException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
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

    @Embedded
    private OrderDelivery delivery;

    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.ORDERING;

    private int totalPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public Order(User user, OrderDelivery delivery, String paymentMethod, int totalPrice) {
        this.user = user;
        user.registerOrder(this);
        this.delivery = delivery;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
    }

    public void validateHasSameUser(User user) {
        if(!this.user.equals(user)) {
            throw new DifferentUserException();
        }
    }

    public void cancel() {
        orderStatus = OrderStatus.CANCEL;

        for(OrderItem orderItem: orderItems) {
            orderItem.cancel();
        }
    }

    public void removeQuantity() {
        for(OrderItem orderItem: orderItems) {
            orderItem.removeQuantity();
        }
    }

    public boolean isOrdering() {
        return orderStatus == OrderStatus.ORDERING;
    }

    public void changeCancelling() {
        if(delivery.isCompleted()) {
            throw new DeliveryAlreadyCompletedException();
        }

        orderStatus = OrderStatus.CANCELLING;
    }

    public void registerTrackingNumber(String deliveryTrackingNumber) {
        delivery.registerTrackingNumber(deliveryTrackingNumber);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public void changeStatus(OrderStatus status) {
        this.orderStatus = status;
    }

    public void order() {
        this.orderStatus = OrderStatus.ORDER;
    }

    public void validateAmount(int amount) {
        if(totalPrice != amount) {
            throw new InvalidAmountException();
        }
    }

    public boolean isCanceled() {
        return orderStatus == OrderStatus.CANCEL;
    }
}

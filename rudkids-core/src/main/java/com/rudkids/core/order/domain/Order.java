package com.rudkids.core.order.domain;

import com.rudkids.core.common.domain.AbstractEntity;
import com.rudkids.core.order.exception.OrderDeliverNotReadyException;
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
    private static final String VIRTUAL_ACCOUNT_CANCEL_REASON = "단순변심";

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "order_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Embedded
    private OrderDelivery delivery;

    @Embedded
    private OrderPayment payment;

    @Embedded
    private VirtualAccount virtualAccount;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private int totalPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public Order(User user, OrderDelivery delivery, int totalPrice) {
        this.user = user;
        user.registerOrder(this);
        this.delivery = delivery;
        this.totalPrice = totalPrice;
    }

    public void registerPaymentInfo(OrderPayment payment) {
        this.payment = payment;
    }

    public void registerVirtualAccount(VirtualAccount virtualAccount) {
        this.virtualAccount = virtualAccount;
    }

    public String getPaymentKey() {
        return payment.getPaymentKey();
    }

    public void validateHasSameUser(User user) {
        if(!this.user.equals(user)) {
            throw new DifferentUserException();
        }
    }

    public boolean isVirtualAccount() {
        return payment.isVirtualAccount();
    }

    public boolean isDeliveryComp() {
        return orderStatus == OrderStatus.DELIVERY_COMP;
    }

    public void cancel() {
        if(orderStatus != OrderStatus.DELIVERY_READY) {
            throw new OrderDeliverNotReadyException();
        }

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

    public void changeDeliveryStatusComp() {
        orderStatus = OrderStatus.DELIVERY_COMP;
    }

    public boolean isVirtualAccountDepositDateExpired() {
        return payment.isVirtualAccount() && virtualAccount.isExpireDueDate();
    }

    public String getCustomerName() {
        return user.getName();
    }

    public String getOrderStatus() {
        return orderStatus.getDescription();
    }

    public void registerDeliveryTrackingNumber(String deliveryTrackingNumber) {
        delivery.registerTrackingNumber(deliveryTrackingNumber);
        orderStatus = OrderStatus.DELIVERY_ING;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public void order() {
        this.orderStatus = OrderStatus.ORDER;
    }

    public void orderVirtualAccount() {
        this.orderStatus = OrderStatus.DEPOSIT_READY;
    }

    public void validateAmount(int amount) {
        if(totalPrice != amount) {
            throw new InvalidAmountException();
        }
    }

    public boolean isDelivering() {
        return orderStatus == OrderStatus.DELIVERY_ING;
    }

    public boolean isCanceled() {
        return orderStatus == OrderStatus.CANCEL;
    }

    public String getDeliveryReceiverName() {
        return delivery.getReceiverName();
    }

    public String getDeliveryReceivedAddress() {
        return delivery.getReceivedAddress();
    }

    public String getDeliveryTrackingNumber() {
        return delivery.getTrackingNumber();
    }

    public String getPaymentMethod() {
        return payment.getMethod();
    }

    public String getVirtualBankName() {
        return virtualAccount.getBankName();
    }

    public String getVirtualAccountNumber() {
        return virtualAccount.getAccountNumber();
    }

    public String getVirtualAccountHolderName() {
        return virtualAccount.getCustomerName();
    }

    public String getRefundBankName() {
        return virtualAccount.getRefundBankCode();
    }

    public String getRefundAccountNumber() {
        return virtualAccount.getRefundAccountName();
    }

    public String getRefundHolderName() {
        return virtualAccount.getRefundHolderName();
    }

    public String getVirtualAccountCancelReason() {
        return VIRTUAL_ACCOUNT_CANCEL_REASON;
    }
}

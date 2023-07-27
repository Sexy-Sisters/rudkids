package com.rudkids.core.order.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderPayment {

    @Column(name = "payment_key", nullable = false)
    private String paymentKey;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private OrderPayment(String paymentKey, PaymentMethod paymentMethod) {
        this.paymentKey = paymentKey;
        this.paymentMethod = paymentMethod;
    }

    public static OrderPayment create(String paymentKey, PaymentMethod paymentMethod) {
        return new OrderPayment(paymentKey, paymentMethod);
    }

    public String getMethod() {
        return paymentMethod.getDescription();
    }

    public boolean isVirtualAccount() {
        return paymentMethod.isVirtualAccount();
    }
}

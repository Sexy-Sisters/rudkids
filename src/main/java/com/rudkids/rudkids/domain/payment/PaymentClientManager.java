package com.rudkids.rudkids.domain.payment;

public interface PaymentClientManager {

    void confirm(PaymentCommand.Confirm command);
}

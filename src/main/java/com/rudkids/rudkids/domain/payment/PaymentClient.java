package com.rudkids.rudkids.domain.payment;

public interface PaymentClient {

    void confirm(PaymentCommand.Confirm command);
}

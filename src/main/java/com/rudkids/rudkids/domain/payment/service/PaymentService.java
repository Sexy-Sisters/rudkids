package com.rudkids.rudkids.domain.payment.service;

import com.rudkids.rudkids.domain.payment.PaymentCommand;

public interface PaymentService {
    void validateAndConfirm(PaymentCommand.Confirm command);
}

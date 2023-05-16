package com.rudkids.rudkids.domain.payment.service;

import com.rudkids.rudkids.domain.order.OrderReader;
import com.rudkids.rudkids.domain.payment.PaymentClient;
import com.rudkids.rudkids.domain.payment.PaymentCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentClient paymentClient;
    private final OrderReader orderReader;

    @Override
    public void validateAndConfirm(PaymentCommand.Confirm command) {
        var order = orderReader.getOrder(command.orderId());
        order.validateAmount(command.amount());
        paymentClient.confirm(command);
        order.changeOrderComplete();
    }
}

package com.rudkids.core.payment.service;

import com.rudkids.core.order.domain.OrderRepository;
import com.rudkids.core.payment.dto.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final OrderRepository orderRepository;
    private final PaymentClientManager paymentClientManager;

    @Override
    public void validateAndConfirm(PaymentRequest.Confirm request) {
        var order = orderRepository.get(request.orderId());
        order.validateAmount(request.amount());
        paymentClientManager.confirm(request);
        order.changeOrderComplete();
    }
}

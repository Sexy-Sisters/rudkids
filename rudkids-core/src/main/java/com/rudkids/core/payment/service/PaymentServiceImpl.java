package com.rudkids.core.payment.service;

import com.rudkids.core.cart.domain.CartItemRepository;
import com.rudkids.core.order.domain.OrderRepository;
import com.rudkids.core.payment.domain.PaymentName;
import com.rudkids.core.payment.dto.PaymentRequest;
import com.rudkids.core.payment.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final PaymentClientManager paymentClientManager;

    @Override
    public PaymentResponse.Info getInformation(UUID userId, UUID orderId) {
        var order = orderRepository.get(orderId);

        var paymentName = PaymentName.create(order.getFirstOrderItemName(), order.getOrderItemSize());
        return new PaymentResponse.Info(order.getId(), paymentName.getName(), userId, order.getTotalPrice());
    }

    @Override
    public void confirm(PaymentRequest.Confirm request) {
        var order = orderRepository.get(request.orderId());
        order.validateAmount(request.amount());
        paymentClientManager.confirm(request);

        cartItemRepository.deleteSelected();
        order.removeQuantity();
        order.order();
    }

    @Override
    public void cancel(UUID orderId, PaymentRequest.Cancel request) {
        var order = orderRepository.get(orderId);
        paymentClientManager.cancel(request);
        order.cancel();
    }
}

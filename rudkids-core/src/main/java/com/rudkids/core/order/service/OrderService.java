package com.rudkids.core.order.service;

import com.rudkids.core.cart.domain.CartItemRepository;
import com.rudkids.core.cart.domain.CartRepository;
import com.rudkids.core.collection.service.Collector;
import com.rudkids.core.common.RandomGeneratable;
import com.rudkids.core.order.domain.*;
import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.dto.OrderResponse;
import com.rudkids.core.order.infrastructure.OrderNameGenerator;
import com.rudkids.core.order.infrastructure.dto.TossPaymentResponse;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private static final int PAYMENT_ORDER_ID_LENGTH = 14;

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final RandomGeneratable randomGeneratable;
    private final OrderNameGenerator orderNameGenerator;
    private final OrderFactory orderFactory;
    private final CartItemRepository cartItemRepository;
    private final PaymentClient paymentClient;
    private final DeliveryTracker deliveryTracker;
    private final Collector collector;
    private final NotificationMessenger notificationMessenger;

    @Transactional(readOnly = true)
    public OrderResponse.PaymentWidgetInfo getPaymentWidgetInfo(UUID userId) {
        var user = userRepository.getUser(userId);
        var cart = cartRepository.get(user);
        var orderId = randomGeneratable.generate(PAYMENT_ORDER_ID_LENGTH);
        var orderName = orderNameGenerator.generate(cart.getSelectedCartItems());

        return OrderResponse.PaymentWidgetInfo.builder()
            .customerKey(user.getId().toString())
            .price(cart.calculateSelectedCartItemsTotalPrice())
            .paymentOrderId(orderId)
            .orderName(orderName)
            .customerName(user.getName())
            .customerEmail(user.getEmail())
            .build();
    }

    public OrderResponse.Id order(UUID userId, OrderRequest.OrderAndPayment request) {
        var user = userRepository.getUser(userId);
        var order = orderFactory.save(user, request);
        order.validateAmount(request.amount());

        var paymentResponse = confirmPayment(order, request);
        var virtualAccount = paymentResponse.toEntity();
        order.registerVirtualAccount(virtualAccount);
        if(order.isVirtualAccount()) {
            order.orderVirtualAccount();
        }

        orderRepository.save(order);
        cartItemRepository.deleteSelected();
        order.removeQuantity();
        return new OrderResponse.Id(order.getId());
    }

    private TossPaymentResponse.Info confirmPayment(Order order, OrderRequest.OrderAndPayment request) {
        var confirmResponse = paymentClient.confirm(request.paymentKey(), request.orderId(), request.amount());
        var orderPayment = OrderPayment.create(request.paymentKey(), PaymentMethod.toEnum(confirmResponse.method()));
        order.registerPaymentInfo(orderPayment);
        order.order();

        return confirmResponse;
    }

    @Transactional(readOnly = true)
    public OrderResponse.Detail get(UUID orderId) {
        var order = orderRepository.get(orderId);
        return new OrderResponse.Detail(order);
    }

    public List<OrderResponse.Main> getAll(UUID userId) {
        var user = userRepository.getUser(userId);
        return orderRepository.getOrders(user).stream()
            .map(order -> {
                if (!order.isDeliveryComp()) {
                    checkDeliveryTracking(user, order);
                }

                if(order.isVirtualAccountDepositDateExpired()) {
                    paymentClient.cancelVirtualAccount(order, order.getVirtualAccountCancelReason());
                }
                return new OrderResponse.Main(order);
            })
            .toList();
    }

    private void checkDeliveryTracking(User user, Order order) {
        if (deliveryTracker.isDeliveryCompleted(user, order)) {
            order.changeDeliveryStatusComp();
            collector.collect(user, order);

            /*
            알림톡 보류
            notificationMessenger.sendDeliveryCompleted(user);
             */
        }
    }

    public OrderResponse.Id cancel(UUID userId, UUID orderId, OrderRequest.PaymentCancel request) {
        var user = userRepository.getUser(userId);
        var order = orderRepository.get(orderId);
        order.validateHasSameUser(user);

        if (order.isVirtualAccount()) {
            paymentClient.cancelVirtualAccount(order, request.cancelReason());
            order.cancel();
            return new OrderResponse.Id(orderId);
        }

        paymentClient.cancel(order.getPaymentKey(), request.cancelReason());
        order.cancel();
        return new OrderResponse.Id(orderId);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse.Main> getCancelOrders(UUID userId) {
        var user = userRepository.getUser(userId);
        return user.getOrders().stream()
            .filter(Order::isCanceled)
            .map(OrderResponse.Main::new)
            .toList();
    }

    public void delete(UUID userId, UUID orderId) {
        var user = userRepository.getUser(userId);
        var order = orderRepository.get(orderId);
        order.validateHasSameUser(user);
        orderRepository.delete(order);
    }
}

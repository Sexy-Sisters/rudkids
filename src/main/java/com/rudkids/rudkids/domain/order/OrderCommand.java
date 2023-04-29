package com.rudkids.rudkids.domain.order;

import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.order.domain.DeliveryFragment;
import com.rudkids.rudkids.domain.order.domain.Order;
import com.rudkids.rudkids.domain.order.domain.PayMethod;
import lombok.Builder;

public class OrderCommand {

    @Builder
    public record CreateRequest(
        PayMethod payMethod,
        String receiverName,
        String receiverPhone,
        String receiverZipcode,
        String receiverAddress1,
        String receiverAddress2,
        String etcMessage
    ) {
        public Order toEntity(Cart cart) {
            var deliveryFragment = DeliveryFragment.builder()
                .receiverName(receiverName)
                .receiverPhone(receiverPhone)
                .receiverZipcode(receiverZipcode)
                .receiverAddress1(receiverAddress1)
                .receiverAddress2(receiverAddress2)
                .etcMessage(etcMessage)
                .build();

            return Order.builder()
                .payMethod(payMethod)
                .deliveryFragment(deliveryFragment)
                .cart(cart)
                .build();
        }
    }
}

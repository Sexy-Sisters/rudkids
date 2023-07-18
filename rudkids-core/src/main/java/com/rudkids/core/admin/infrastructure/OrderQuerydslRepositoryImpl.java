package com.rudkids.core.admin.infrastructure;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rudkids.core.admin.domain.OrderQuerydslRepository;
import com.rudkids.core.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.rudkids.core.order.domain.QOrder.order;

@Repository
@RequiredArgsConstructor
public class OrderQuerydslRepositoryImpl implements OrderQuerydslRepository {
    private final JPAQueryFactory factory;

    @Override
    public Page<Order> getOrders(String deliveryStatus, String orderStatus, String deliveryTrackingNumber, String customerName, Pageable pageable) {
        return new PageImpl<>(
            factory
                .selectFrom(order)
                .where(
                    checkDeliveryStatus(deliveryStatus)
                        .or(checkOrderStatus(orderStatus))
                        .or(checkDeliveryTrackingNumber(deliveryTrackingNumber))
                        .or(checkCustomerName(customerName)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
        );
    }

    private BooleanExpression checkDeliveryStatus(String deliveryStatus) {
        if (deliveryStatus == null) {
            return null;
        }
        return order.delivery.deliveryStatus.stringValue().eq(deliveryStatus);
    }

    private BooleanExpression checkOrderStatus(String orderStatus) {
        if (orderStatus == null) {
            return null;
        }
        return order.orderStatus.stringValue().eq(orderStatus);
    }

    private BooleanExpression checkDeliveryTrackingNumber(String deliveryTrackingNumber) {
        if (deliveryTrackingNumber == null) {
            return null;
        }
        return order.delivery.trackingNumber.eq(deliveryTrackingNumber);
    }

    private BooleanExpression checkCustomerName(String customerName) {
        if (customerName == null) {
            return null;
        }
        return order.user.name.value.eq(customerName);
    }
}

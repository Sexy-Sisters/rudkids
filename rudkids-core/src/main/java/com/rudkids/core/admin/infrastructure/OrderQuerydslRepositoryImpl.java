package com.rudkids.core.admin.infrastructure;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rudkids.core.admin.domain.OrderQuerydslRepository;
import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.domain.OrderStatus;
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
    public Page<Order> getOrders(OrderStatus orderStatus, String deliveryTrackingNumber, String customerName, Pageable pageable) {
        BooleanExpression conditions;

        conditions = checkOrderStatus(orderStatus);
        conditions = addCondition(conditions, checkDeliveryTrackingNumber(deliveryTrackingNumber));
        conditions = addCondition(conditions, checkCustomerName(customerName));

        return new PageImpl<>(
            factory
                .selectFrom(order)
                .where(conditions)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
        );
    }

    private BooleanExpression checkOrderStatus(OrderStatus orderStatus) {
        return order.orderStatus.eq(orderStatus);
    }

    private BooleanExpression checkDeliveryTrackingNumber(String deliveryTrackingNumber) {
        if (StringUtils.isNullOrEmpty(deliveryTrackingNumber)) {
            return null;
        }
        return order.delivery.trackingNumber.eq(deliveryTrackingNumber);
    }

    private BooleanExpression checkCustomerName(String customerName) {
        if (StringUtils.isNullOrEmpty(customerName)) {
            return null;
        }
        return order.user.name.value.containsIgnoreCase(customerName);
    }

    private BooleanExpression addCondition(BooleanExpression accumulatedConditions, BooleanExpression newCondition) {
        if (newCondition == null) {
            return accumulatedConditions;
        }
        return accumulatedConditions.and(newCondition);
    }
}

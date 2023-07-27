package com.rudkids.core.order.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderPayment is a Querydsl query type for OrderPayment
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QOrderPayment extends BeanPath<OrderPayment> {

    private static final long serialVersionUID = 1372161600L;

    public static final QOrderPayment orderPayment = new QOrderPayment("orderPayment");

    public final StringPath paymentKey = createString("paymentKey");

    public final EnumPath<PaymentMethod> paymentMethod = createEnum("paymentMethod", PaymentMethod.class);

    public QOrderPayment(String variable) {
        super(OrderPayment.class, forVariable(variable));
    }

    public QOrderPayment(Path<? extends OrderPayment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderPayment(PathMetadata metadata) {
        super(OrderPayment.class, metadata);
    }

}


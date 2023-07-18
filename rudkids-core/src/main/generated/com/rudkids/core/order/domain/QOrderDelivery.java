package com.rudkids.core.order.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderDelivery is a Querydsl query type for OrderDelivery
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QOrderDelivery extends BeanPath<OrderDelivery> {

    private static final long serialVersionUID = -971878662L;

    public static final QOrderDelivery orderDelivery = new QOrderDelivery("orderDelivery");

    public final EnumPath<OrderDeliveryStatus> deliveryStatus = createEnum("deliveryStatus", OrderDeliveryStatus.class);

    public final StringPath message = createString("message");

    public final StringPath receivedAddress = createString("receivedAddress");

    public final StringPath receiverName = createString("receiverName");

    public final StringPath receiverPhone = createString("receiverPhone");

    public final StringPath trackingNumber = createString("trackingNumber");

    public QOrderDelivery(String variable) {
        super(OrderDelivery.class, forVariable(variable));
    }

    public QOrderDelivery(Path<? extends OrderDelivery> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderDelivery(PathMetadata metadata) {
        super(OrderDelivery.class, metadata);
    }

}


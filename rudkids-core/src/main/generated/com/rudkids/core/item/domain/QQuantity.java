package com.rudkids.core.item.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QQuantity is a Querydsl query type for Quantity
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QQuantity extends BeanPath<Quantity> {

    private static final long serialVersionUID = -458515972L;

    public static final QQuantity quantity = new QQuantity("quantity");

    public final NumberPath<Integer> value = createNumber("value", Integer.class);

    public QQuantity(String variable) {
        super(Quantity.class, forVariable(variable));
    }

    public QQuantity(Path<? extends Quantity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuantity(PathMetadata metadata) {
        super(Quantity.class, metadata);
    }

}


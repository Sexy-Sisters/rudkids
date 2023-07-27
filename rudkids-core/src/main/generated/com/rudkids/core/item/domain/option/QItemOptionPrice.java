package com.rudkids.core.item.domain.option;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemOptionPrice is a Querydsl query type for ItemOptionPrice
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QItemOptionPrice extends BeanPath<ItemOptionPrice> {

    private static final long serialVersionUID = 1623462073L;

    public static final QItemOptionPrice itemOptionPrice = new QItemOptionPrice("itemOptionPrice");

    public final NumberPath<Integer> value = createNumber("value", Integer.class);

    public QItemOptionPrice(String variable) {
        super(ItemOptionPrice.class, forVariable(variable));
    }

    public QItemOptionPrice(Path<? extends ItemOptionPrice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemOptionPrice(PathMetadata metadata) {
        super(ItemOptionPrice.class, metadata);
    }

}


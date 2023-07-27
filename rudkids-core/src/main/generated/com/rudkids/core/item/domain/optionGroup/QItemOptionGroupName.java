package com.rudkids.core.item.domain.optionGroup;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemOptionGroupName is a Querydsl query type for ItemOptionGroupName
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QItemOptionGroupName extends BeanPath<ItemOptionGroupName> {

    private static final long serialVersionUID = 1827623853L;

    public static final QItemOptionGroupName itemOptionGroupName = new QItemOptionGroupName("itemOptionGroupName");

    public final StringPath value = createString("value");

    public QItemOptionGroupName(String variable) {
        super(ItemOptionGroupName.class, forVariable(variable));
    }

    public QItemOptionGroupName(Path<? extends ItemOptionGroupName> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemOptionGroupName(PathMetadata metadata) {
        super(ItemOptionGroupName.class, metadata);
    }

}


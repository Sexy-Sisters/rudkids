package com.rudkids.core.item.domain.itemOption;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemOptionName is a Querydsl query type for ItemOptionName
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QItemOptionName extends BeanPath<ItemOptionName> {

    private static final long serialVersionUID = 1508981832L;

    public static final QItemOptionName itemOptionName = new QItemOptionName("itemOptionName");

    public final StringPath value = createString("value");

    public QItemOptionName(String variable) {
        super(ItemOptionName.class, forVariable(variable));
    }

    public QItemOptionName(Path<? extends ItemOptionName> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemOptionName(PathMetadata metadata) {
        super(ItemOptionName.class, metadata);
    }

}


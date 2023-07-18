package com.rudkids.core.item.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemBio is a Querydsl query type for ItemBio
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QItemBio extends BeanPath<ItemBio> {

    private static final long serialVersionUID = 64628068L;

    public static final QItemBio itemBio = new QItemBio("itemBio");

    public final StringPath value = createString("value");

    public QItemBio(String variable) {
        super(ItemBio.class, forVariable(variable));
    }

    public QItemBio(Path<? extends ItemBio> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemBio(PathMetadata metadata) {
        super(ItemBio.class, metadata);
    }

}


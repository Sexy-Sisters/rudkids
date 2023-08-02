package com.rudkids.core.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBio is a Querydsl query type for Bio
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBio extends BeanPath<Bio> {

    private static final long serialVersionUID = -654777377L;

    public static final QBio bio = new QBio("bio");

    public final StringPath value = createString("value");

    public QBio(String variable) {
        super(Bio.class, forVariable(variable));
    }

    public QBio(Path<? extends Bio> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBio(PathMetadata metadata) {
        super(Bio.class, metadata);
    }

}


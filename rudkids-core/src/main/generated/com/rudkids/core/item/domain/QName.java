package com.rudkids.core.item.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QName is a Querydsl query type for Name
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QName extends BeanPath<Name> {

    private static final long serialVersionUID = -1056632708L;

    public static final QName name = new QName("name");

    public final StringPath enName = createString("enName");

    public final StringPath koName = createString("koName");

    public QName(String variable) {
        super(Name.class, forVariable(variable));
    }

    public QName(Path<? extends Name> path) {
        super(path.getType(), path.getMetadata());
    }

    public QName(PathMetadata metadata) {
        super(Name.class, metadata);
    }

}


package com.rudkids.core.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBackImage is a Querydsl query type for BackImage
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBackImage extends BeanPath<BackImage> {

    private static final long serialVersionUID = 2064275019L;

    public static final QBackImage backImage = new QBackImage("backImage");

    public final StringPath path = createString("path");

    public final StringPath url = createString("url");

    public QBackImage(String variable) {
        super(BackImage.class, forVariable(variable));
    }

    public QBackImage(Path<? extends BackImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBackImage(PathMetadata metadata) {
        super(BackImage.class, metadata);
    }

}


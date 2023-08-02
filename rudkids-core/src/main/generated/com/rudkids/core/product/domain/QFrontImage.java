package com.rudkids.core.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFrontImage is a Querydsl query type for FrontImage
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QFrontImage extends BeanPath<FrontImage> {

    private static final long serialVersionUID = 1419564539L;

    public static final QFrontImage frontImage = new QFrontImage("frontImage");

    public final StringPath path = createString("path");

    public final StringPath url = createString("url");

    public QFrontImage(String variable) {
        super(FrontImage.class, forVariable(variable));
    }

    public QFrontImage(Path<? extends FrontImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFrontImage(PathMetadata metadata) {
        super(FrontImage.class, metadata);
    }

}


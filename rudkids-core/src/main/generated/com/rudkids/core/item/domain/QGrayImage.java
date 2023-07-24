package com.rudkids.core.item.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGrayImage is a Querydsl query type for GrayImage
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QGrayImage extends BeanPath<GrayImage> {

    private static final long serialVersionUID = -1092571801L;

    public static final QGrayImage grayImage = new QGrayImage("grayImage");

    public final StringPath path = createString("path");

    public final StringPath url = createString("url");

    public QGrayImage(String variable) {
        super(GrayImage.class, forVariable(variable));
    }

    public QGrayImage(Path<? extends GrayImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGrayImage(PathMetadata metadata) {
        super(GrayImage.class, metadata);
    }

}


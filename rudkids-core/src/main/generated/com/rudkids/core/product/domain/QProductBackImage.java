package com.rudkids.core.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductBackImage is a Querydsl query type for ProductBackImage
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QProductBackImage extends BeanPath<ProductBackImage> {

    private static final long serialVersionUID = 496552718L;

    public static final QProductBackImage productBackImage = new QProductBackImage("productBackImage");

    public final StringPath path = createString("path");

    public final StringPath url = createString("url");

    public QProductBackImage(String variable) {
        super(ProductBackImage.class, forVariable(variable));
    }

    public QProductBackImage(Path<? extends ProductBackImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductBackImage(PathMetadata metadata) {
        super(ProductBackImage.class, metadata);
    }

}


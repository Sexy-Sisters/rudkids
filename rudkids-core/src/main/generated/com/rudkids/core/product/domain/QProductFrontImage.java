package com.rudkids.core.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductFrontImage is a Querydsl query type for ProductFrontImage
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QProductFrontImage extends BeanPath<ProductFrontImage> {

    private static final long serialVersionUID = 64813464L;

    public static final QProductFrontImage productFrontImage = new QProductFrontImage("productFrontImage");

    public final StringPath path = createString("path");

    public final StringPath url = createString("url");

    public QProductFrontImage(String variable) {
        super(ProductFrontImage.class, forVariable(variable));
    }

    public QProductFrontImage(Path<? extends ProductFrontImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductFrontImage(PathMetadata metadata) {
        super(ProductFrontImage.class, metadata);
    }

}


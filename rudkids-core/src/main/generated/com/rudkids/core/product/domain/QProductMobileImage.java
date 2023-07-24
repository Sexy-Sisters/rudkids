package com.rudkids.core.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductMobileImage is a Querydsl query type for ProductMobileImage
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QProductMobileImage extends BeanPath<ProductMobileImage> {

    private static final long serialVersionUID = -1528353357L;

    public static final QProductMobileImage productMobileImage = new QProductMobileImage("productMobileImage");

    public final StringPath path = createString("path");

    public final StringPath url = createString("url");

    public QProductMobileImage(String variable) {
        super(ProductMobileImage.class, forVariable(variable));
    }

    public QProductMobileImage(Path<? extends ProductMobileImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductMobileImage(PathMetadata metadata) {
        super(ProductMobileImage.class, metadata);
    }

}


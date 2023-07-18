package com.rudkids.core.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductBio is a Querydsl query type for ProductBio
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QProductBio extends BeanPath<ProductBio> {

    private static final long serialVersionUID = -777853214L;

    public static final QProductBio productBio = new QProductBio("productBio");

    public final StringPath value = createString("value");

    public QProductBio(String variable) {
        super(ProductBio.class, forVariable(variable));
    }

    public QProductBio(Path<? extends ProductBio> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductBio(PathMetadata metadata) {
        super(ProductBio.class, metadata);
    }

}


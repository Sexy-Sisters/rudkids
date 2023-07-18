package com.rudkids.core.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductBannerImage is a Querydsl query type for ProductBannerImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductBannerImage extends EntityPathBase<ProductBannerImage> {

    private static final long serialVersionUID = -2121415319L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductBannerImage productBannerImage = new QProductBannerImage("productBannerImage");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final NumberPath<Integer> ordering = createNumber("ordering", Integer.class);

    public final StringPath path = createString("path");

    public final QProduct product;

    public final StringPath url = createString("url");

    public QProductBannerImage(String variable) {
        this(ProductBannerImage.class, forVariable(variable), INITS);
    }

    public QProductBannerImage(Path<? extends ProductBannerImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductBannerImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductBannerImage(PathMetadata metadata, PathInits inits) {
        this(ProductBannerImage.class, metadata, inits);
    }

    public QProductBannerImage(Class<? extends ProductBannerImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}


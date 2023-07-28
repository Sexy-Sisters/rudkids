package com.rudkids.core.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 473428038L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final com.rudkids.core.common.domain.QAbstractEntity _super = new com.rudkids.core.common.domain.QAbstractEntity(this);

    public final QProductBackImage backImage;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdAt = _super.createdAt;

    public final QProductFrontImage frontImage;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final ListPath<com.rudkids.core.item.domain.Item, com.rudkids.core.item.domain.QItem> items = this.<com.rudkids.core.item.domain.Item, com.rudkids.core.item.domain.QItem>createList("items", com.rudkids.core.item.domain.Item.class, com.rudkids.core.item.domain.QItem.class, PathInits.DIRECT2);

    public final QProductMobileImage mobileImage;

    public final ListPath<ProductBannerImage, QProductBannerImage> productBannerImages = this.<ProductBannerImage, QProductBannerImage>createList("productBannerImages", ProductBannerImage.class, QProductBannerImage.class, PathInits.DIRECT2);

    public final QProductBio productBio;

    public final EnumPath<ProductStatus> productStatus = createEnum("productStatus", ProductStatus.class);

    public final QTitle title;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updatedAt = _super.updatedAt;

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.backImage = inits.isInitialized("backImage") ? new QProductBackImage(forProperty("backImage")) : null;
        this.frontImage = inits.isInitialized("frontImage") ? new QProductFrontImage(forProperty("frontImage")) : null;
        this.mobileImage = inits.isInitialized("mobileImage") ? new QProductMobileImage(forProperty("mobileImage")) : null;
        this.productBio = inits.isInitialized("productBio") ? new QProductBio(forProperty("productBio")) : null;
        this.title = inits.isInitialized("title") ? new QTitle(forProperty("title")) : null;
    }

}


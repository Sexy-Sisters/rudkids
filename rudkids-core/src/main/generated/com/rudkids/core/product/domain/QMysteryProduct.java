package com.rudkids.core.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMysteryProduct is a Querydsl query type for MysteryProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMysteryProduct extends EntityPathBase<MysteryProduct> {

    private static final long serialVersionUID = -1708024967L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMysteryProduct mysteryProduct = new QMysteryProduct("mysteryProduct");

    public final QBackImage backImage;

    public final QBannerImage bannerImage;

    public final QBio bio;

    public final QFrontImage frontImage;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final ListPath<com.rudkids.core.item.domain.Item, com.rudkids.core.item.domain.QItem> items = this.<com.rudkids.core.item.domain.Item, com.rudkids.core.item.domain.QItem>createList("items", com.rudkids.core.item.domain.Item.class, com.rudkids.core.item.domain.QItem.class, PathInits.DIRECT2);

    public final QTitle title;

    public QMysteryProduct(String variable) {
        this(MysteryProduct.class, forVariable(variable), INITS);
    }

    public QMysteryProduct(Path<? extends MysteryProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMysteryProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMysteryProduct(PathMetadata metadata, PathInits inits) {
        this(MysteryProduct.class, metadata, inits);
    }

    public QMysteryProduct(Class<? extends MysteryProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.backImage = inits.isInitialized("backImage") ? new QBackImage(forProperty("backImage")) : null;
        this.bannerImage = inits.isInitialized("bannerImage") ? new QBannerImage(forProperty("bannerImage")) : null;
        this.bio = inits.isInitialized("bio") ? new QBio(forProperty("bio")) : null;
        this.frontImage = inits.isInitialized("frontImage") ? new QFrontImage(forProperty("frontImage")) : null;
        this.title = inits.isInitialized("title") ? new QTitle(forProperty("title")) : null;
    }

}


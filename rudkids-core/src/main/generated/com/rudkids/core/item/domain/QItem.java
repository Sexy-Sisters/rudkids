package com.rudkids.core.item.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = -1056763644L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItem item = new QItem("item");

    public final com.rudkids.core.common.domain.QAbstractEntity _super = new com.rudkids.core.common.domain.QAbstractEntity(this);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdAt = _super.createdAt;

    public final QGrayImage grayImage;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final ListPath<ItemImage, QItemImage> images = this.<ItemImage, QItemImage>createList("images", ItemImage.class, QItemImage.class, PathInits.DIRECT2);

    public final QItemBio itemBio;

    public final ListPath<com.rudkids.core.item.domain.optionGroup.ItemOptionGroup, com.rudkids.core.item.domain.optionGroup.QItemOptionGroup> itemOptionGroups = this.<com.rudkids.core.item.domain.optionGroup.ItemOptionGroup, com.rudkids.core.item.domain.optionGroup.QItemOptionGroup>createList("itemOptionGroups", com.rudkids.core.item.domain.optionGroup.ItemOptionGroup.class, com.rudkids.core.item.domain.optionGroup.QItemOptionGroup.class, PathInits.DIRECT2);

    public final EnumPath<ItemStatus> itemStatus = createEnum("itemStatus", ItemStatus.class);

    public final EnumPath<LimitType> limitType = createEnum("limitType", LimitType.class);

    public final StringPath mysteryItemName = createString("mysteryItemName");

    public final QName name;

    public final QPrice price;

    public final com.rudkids.core.product.domain.QProduct product;

    public final QQuantity quantity;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updatedAt = _super.updatedAt;

    public final StringPath videoUrl = createString("videoUrl");

    public QItem(String variable) {
        this(Item.class, forVariable(variable), INITS);
    }

    public QItem(Path<? extends Item> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItem(PathMetadata metadata, PathInits inits) {
        this(Item.class, metadata, inits);
    }

    public QItem(Class<? extends Item> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.grayImage = inits.isInitialized("grayImage") ? new QGrayImage(forProperty("grayImage")) : null;
        this.itemBio = inits.isInitialized("itemBio") ? new QItemBio(forProperty("itemBio")) : null;
        this.name = inits.isInitialized("name") ? new QName(forProperty("name")) : null;
        this.price = inits.isInitialized("price") ? new QPrice(forProperty("price")) : null;
        this.product = inits.isInitialized("product") ? new com.rudkids.core.product.domain.QProduct(forProperty("product"), inits.get("product")) : null;
        this.quantity = inits.isInitialized("quantity") ? new QQuantity(forProperty("quantity")) : null;
    }

}


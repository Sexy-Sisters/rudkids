package com.rudkids.core.collection.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCollectionItem is a Querydsl query type for CollectionItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCollectionItem extends EntityPathBase<CollectionItem> {

    private static final long serialVersionUID = 997149517L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCollectionItem collectionItem = new QCollectionItem("collectionItem");

    public final EnumPath<CollectionItemCategory> category = createEnum("category", CollectionItemCategory.class);

    public final QCollection collection;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath itemEnName = createString("itemEnName");

    public final StringPath itemGrayImageUrl = createString("itemGrayImageUrl");

    public final StringPath itemImageUrl = createString("itemImageUrl");

    public QCollectionItem(String variable) {
        this(CollectionItem.class, forVariable(variable), INITS);
    }

    public QCollectionItem(Path<? extends CollectionItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCollectionItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCollectionItem(PathMetadata metadata, PathInits inits) {
        this(CollectionItem.class, metadata, inits);
    }

    public QCollectionItem(Class<? extends CollectionItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.collection = inits.isInitialized("collection") ? new QCollection(forProperty("collection")) : null;
    }

}


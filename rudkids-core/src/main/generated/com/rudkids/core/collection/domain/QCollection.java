package com.rudkids.core.collection.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCollection is a Querydsl query type for Collection
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCollection extends EntityPathBase<Collection> {

    private static final long serialVersionUID = 1103264026L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCollection collection = new QCollection("collection");

    public final ListPath<CollectionItem, QCollectionItem> collectionItems = this.<CollectionItem, QCollectionItem>createList("collectionItems", CollectionItem.class, QCollectionItem.class, PathInits.DIRECT2);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final com.rudkids.core.user.domain.QUser user;

    public QCollection(String variable) {
        this(Collection.class, forVariable(variable), INITS);
    }

    public QCollection(Path<? extends Collection> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCollection(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCollection(PathMetadata metadata, PathInits inits) {
        this(Collection.class, metadata, inits);
    }

    public QCollection(Class<? extends Collection> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.rudkids.core.user.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}


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

    public static final QCollection collection = new QCollection("collection");

    public final ListPath<CollectionItem, QCollectionItem> collectionItems = this.<CollectionItem, QCollectionItem>createList("collectionItems", CollectionItem.class, QCollectionItem.class, PathInits.DIRECT2);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public QCollection(String variable) {
        super(Collection.class, forVariable(variable));
    }

    public QCollection(Path<? extends Collection> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCollection(PathMetadata metadata) {
        super(Collection.class, metadata);
    }

}


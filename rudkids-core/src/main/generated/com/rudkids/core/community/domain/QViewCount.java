package com.rudkids.core.community.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QViewCount is a Querydsl query type for ViewCount
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QViewCount extends BeanPath<ViewCount> {

    private static final long serialVersionUID = -207120697L;

    public static final QViewCount viewCount = new QViewCount("viewCount");

    public final NumberPath<Long> value = createNumber("value", Long.class);

    public QViewCount(String variable) {
        super(ViewCount.class, forVariable(variable));
    }

    public QViewCount(Path<? extends ViewCount> path) {
        super(path.getType(), path.getMetadata());
    }

    public QViewCount(PathMetadata metadata) {
        super(ViewCount.class, metadata);
    }

}


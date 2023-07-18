package com.rudkids.core.community.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommunityImage is a Querydsl query type for CommunityImage
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCommunityImage extends BeanPath<CommunityImage> {

    private static final long serialVersionUID = 1428149173L;

    public static final QCommunityImage communityImage = new QCommunityImage("communityImage");

    public final StringPath path = createString("path");

    public final StringPath url = createString("url");

    public QCommunityImage(String variable) {
        super(CommunityImage.class, forVariable(variable));
    }

    public QCommunityImage(Path<? extends CommunityImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommunityImage(PathMetadata metadata) {
        super(CommunityImage.class, metadata);
    }

}


package com.rudkids.core.communityLike.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommunityLike is a Querydsl query type for CommunityLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommunityLike extends EntityPathBase<CommunityLike> {

    private static final long serialVersionUID = 991466022L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommunityLike communityLike = new QCommunityLike("communityLike");

    public final com.rudkids.core.community.domain.QCommunity community;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final com.rudkids.core.user.domain.QUser user;

    public QCommunityLike(String variable) {
        this(CommunityLike.class, forVariable(variable), INITS);
    }

    public QCommunityLike(Path<? extends CommunityLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommunityLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommunityLike(PathMetadata metadata, PathInits inits) {
        this(CommunityLike.class, metadata, inits);
    }

    public QCommunityLike(Class<? extends CommunityLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.community = inits.isInitialized("community") ? new com.rudkids.core.community.domain.QCommunity(forProperty("community"), inits.get("community")) : null;
        this.user = inits.isInitialized("user") ? new com.rudkids.core.user.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}


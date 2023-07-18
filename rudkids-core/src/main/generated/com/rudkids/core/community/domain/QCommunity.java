package com.rudkids.core.community.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommunity is a Querydsl query type for Community
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommunity extends EntityPathBase<Community> {

    private static final long serialVersionUID = -88358586L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommunity community = new QCommunity("community");

    public final com.rudkids.core.common.domain.QAbstractEntity _super = new com.rudkids.core.common.domain.QAbstractEntity(this);

    public final QCommunityImage communityImage;

    public final ListPath<com.rudkids.core.communityLike.domain.CommunityLike, com.rudkids.core.communityLike.domain.QCommunityLike> communityLikes = this.<com.rudkids.core.communityLike.domain.CommunityLike, com.rudkids.core.communityLike.domain.QCommunityLike>createList("communityLikes", com.rudkids.core.communityLike.domain.CommunityLike.class, com.rudkids.core.communityLike.domain.QCommunityLike.class, PathInits.DIRECT2);

    public final EnumPath<CommunityType> communityType = createEnum("communityType", CommunityType.class);

    public final QContent content;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdAt = _super.createdAt;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final QTitle title;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updatedAt = _super.updatedAt;

    public final com.rudkids.core.user.domain.QUser user;

    public final QViewCount viewCount;

    public QCommunity(String variable) {
        this(Community.class, forVariable(variable), INITS);
    }

    public QCommunity(Path<? extends Community> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommunity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommunity(PathMetadata metadata, PathInits inits) {
        this(Community.class, metadata, inits);
    }

    public QCommunity(Class<? extends Community> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.communityImage = inits.isInitialized("communityImage") ? new QCommunityImage(forProperty("communityImage")) : null;
        this.content = inits.isInitialized("content") ? new QContent(forProperty("content")) : null;
        this.title = inits.isInitialized("title") ? new QTitle(forProperty("title")) : null;
        this.user = inits.isInitialized("user") ? new com.rudkids.core.user.domain.QUser(forProperty("user"), inits.get("user")) : null;
        this.viewCount = inits.isInitialized("viewCount") ? new QViewCount(forProperty("viewCount")) : null;
    }

}


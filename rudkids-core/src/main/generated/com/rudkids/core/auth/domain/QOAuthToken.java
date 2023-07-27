package com.rudkids.core.auth.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOAuthToken is a Querydsl query type for OAuthToken
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOAuthToken extends EntityPathBase<OAuthToken> {

    private static final long serialVersionUID = 228509192L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOAuthToken oAuthToken = new QOAuthToken("oAuthToken");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath kakaoRefreshToken = createString("kakaoRefreshToken");

    public final com.rudkids.core.user.domain.QUser user;

    public QOAuthToken(String variable) {
        this(OAuthToken.class, forVariable(variable), INITS);
    }

    public QOAuthToken(Path<? extends OAuthToken> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOAuthToken(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOAuthToken(PathMetadata metadata, PathInits inits) {
        this(OAuthToken.class, metadata, inits);
    }

    public QOAuthToken(Class<? extends OAuthToken> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.rudkids.core.user.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}


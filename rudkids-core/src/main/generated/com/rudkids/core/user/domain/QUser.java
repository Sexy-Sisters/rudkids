package com.rudkids.core.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1567449484L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final ListPath<com.rudkids.core.community.domain.Community, com.rudkids.core.community.domain.QCommunity> communities = this.<com.rudkids.core.community.domain.Community, com.rudkids.core.community.domain.QCommunity>createList("communities", com.rudkids.core.community.domain.Community.class, com.rudkids.core.community.domain.QCommunity.class, PathInits.DIRECT2);

    public final ListPath<com.rudkids.core.delivery.domain.Delivery, com.rudkids.core.delivery.domain.QDelivery> deliveries = this.<com.rudkids.core.delivery.domain.Delivery, com.rudkids.core.delivery.domain.QDelivery>createList("deliveries", com.rudkids.core.delivery.domain.Delivery.class, com.rudkids.core.delivery.domain.QDelivery.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final QUserName name;

    public final ListPath<com.rudkids.core.order.domain.Order, com.rudkids.core.order.domain.QOrder> orders = this.<com.rudkids.core.order.domain.Order, com.rudkids.core.order.domain.QOrder>createList("orders", com.rudkids.core.order.domain.Order.class, com.rudkids.core.order.domain.QOrder.class, PathInits.DIRECT2);

    public final QPhoneNumber phoneNumber;

    public final QProfileImage profileImage;

    public final EnumPath<RoleType> roleType = createEnum("roleType", RoleType.class);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.name = inits.isInitialized("name") ? new QUserName(forProperty("name")) : null;
        this.phoneNumber = inits.isInitialized("phoneNumber") ? new QPhoneNumber(forProperty("phoneNumber")) : null;
        this.profileImage = inits.isInitialized("profileImage") ? new QProfileImage(forProperty("profileImage")) : null;
    }

}


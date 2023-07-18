package com.rudkids.core.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProfileImage is a Querydsl query type for ProfileImage
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QProfileImage extends BeanPath<ProfileImage> {

    private static final long serialVersionUID = -714028325L;

    public static final QProfileImage profileImage = new QProfileImage("profileImage");

    public final StringPath path = createString("path");

    public final StringPath url = createString("url");

    public QProfileImage(String variable) {
        super(ProfileImage.class, forVariable(variable));
    }

    public QProfileImage(Path<? extends ProfileImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProfileImage(PathMetadata metadata) {
        super(ProfileImage.class, metadata);
    }

}


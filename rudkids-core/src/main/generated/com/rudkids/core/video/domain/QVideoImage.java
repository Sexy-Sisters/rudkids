package com.rudkids.core.video.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVideoImage is a Querydsl query type for VideoImage
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QVideoImage extends BeanPath<VideoImage> {

    private static final long serialVersionUID = 1520953077L;

    public static final QVideoImage videoImage = new QVideoImage("videoImage");

    public final StringPath path = createString("path");

    public final StringPath url = createString("url");

    public QVideoImage(String variable) {
        super(VideoImage.class, forVariable(variable));
    }

    public QVideoImage(Path<? extends VideoImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVideoImage(PathMetadata metadata) {
        super(VideoImage.class, metadata);
    }

}


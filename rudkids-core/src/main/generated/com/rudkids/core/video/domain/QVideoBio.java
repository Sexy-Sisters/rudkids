package com.rudkids.core.video.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVideoBio is a Querydsl query type for VideoBio
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QVideoBio extends BeanPath<VideoBio> {

    private static final long serialVersionUID = 305486114L;

    public static final QVideoBio videoBio = new QVideoBio("videoBio");

    public final StringPath value = createString("value");

    public QVideoBio(String variable) {
        super(VideoBio.class, forVariable(variable));
    }

    public QVideoBio(Path<? extends VideoBio> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVideoBio(PathMetadata metadata) {
        super(VideoBio.class, metadata);
    }

}


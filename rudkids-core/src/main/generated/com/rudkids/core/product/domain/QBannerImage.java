package com.rudkids.core.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBannerImage is a Querydsl query type for BannerImage
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBannerImage extends BeanPath<BannerImage> {

    private static final long serialVersionUID = 1221162342L;

    public static final QBannerImage bannerImage = new QBannerImage("bannerImage");

    public final StringPath bannerPath = createString("bannerPath");

    public final StringPath bannerUrl = createString("bannerUrl");

    public final StringPath mobileBannerPath = createString("mobileBannerPath");

    public final StringPath mobileBannerUrl = createString("mobileBannerUrl");

    public QBannerImage(String variable) {
        super(BannerImage.class, forVariable(variable));
    }

    public QBannerImage(Path<? extends BannerImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBannerImage(PathMetadata metadata) {
        super(BannerImage.class, metadata);
    }

}


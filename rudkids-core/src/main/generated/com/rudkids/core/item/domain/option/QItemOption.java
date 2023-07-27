package com.rudkids.core.item.domain.option;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemOption is a Querydsl query type for ItemOption
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemOption extends EntityPathBase<ItemOption> {

    private static final long serialVersionUID = 239679088L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemOption itemOption = new QItemOption("itemOption");

    public final com.rudkids.core.common.domain.QAbstractEntity _super = new com.rudkids.core.common.domain.QAbstractEntity(this);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdAt = _super.createdAt;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final com.rudkids.core.item.domain.optionGroup.QItemOptionGroup itemOptionGroup;

    public final QItemOptionName itemOptionName;

    public final QItemOptionPrice itemOptionPrice;

    public final NumberPath<Integer> ordering = createNumber("ordering", Integer.class);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updatedAt = _super.updatedAt;

    public QItemOption(String variable) {
        this(ItemOption.class, forVariable(variable), INITS);
    }

    public QItemOption(Path<? extends ItemOption> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemOption(PathMetadata metadata, PathInits inits) {
        this(ItemOption.class, metadata, inits);
    }

    public QItemOption(Class<? extends ItemOption> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.itemOptionGroup = inits.isInitialized("itemOptionGroup") ? new com.rudkids.core.item.domain.optionGroup.QItemOptionGroup(forProperty("itemOptionGroup"), inits.get("itemOptionGroup")) : null;
        this.itemOptionName = inits.isInitialized("itemOptionName") ? new QItemOptionName(forProperty("itemOptionName")) : null;
        this.itemOptionPrice = inits.isInitialized("itemOptionPrice") ? new QItemOptionPrice(forProperty("itemOptionPrice")) : null;
    }

}


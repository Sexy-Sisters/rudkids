package com.rudkids.core.item.domain.optionGroup;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemOptionGroup is a Querydsl query type for ItemOptionGroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemOptionGroup extends EntityPathBase<ItemOptionGroup> {

    private static final long serialVersionUID = 943273474L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemOptionGroup itemOptionGroup = new QItemOptionGroup("itemOptionGroup");

    public final com.rudkids.core.common.domain.QAbstractEntity _super = new com.rudkids.core.common.domain.QAbstractEntity(this);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdAt = _super.createdAt;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final com.rudkids.core.item.domain.QItem item;

    public final QItemOptionGroupName itemOptionGroupName;

    public final ListPath<com.rudkids.core.item.domain.option.ItemOption, com.rudkids.core.item.domain.option.QItemOption> itemOptions = this.<com.rudkids.core.item.domain.option.ItemOption, com.rudkids.core.item.domain.option.QItemOption>createList("itemOptions", com.rudkids.core.item.domain.option.ItemOption.class, com.rudkids.core.item.domain.option.QItemOption.class, PathInits.DIRECT2);

    public final NumberPath<Integer> ordering = createNumber("ordering", Integer.class);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updatedAt = _super.updatedAt;

    public QItemOptionGroup(String variable) {
        this(ItemOptionGroup.class, forVariable(variable), INITS);
    }

    public QItemOptionGroup(Path<? extends ItemOptionGroup> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemOptionGroup(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemOptionGroup(PathMetadata metadata, PathInits inits) {
        this(ItemOptionGroup.class, metadata, inits);
    }

    public QItemOptionGroup(Class<? extends ItemOptionGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new com.rudkids.core.item.domain.QItem(forProperty("item"), inits.get("item")) : null;
        this.itemOptionGroupName = inits.isInitialized("itemOptionGroupName") ? new QItemOptionGroupName(forProperty("itemOptionGroupName")) : null;
    }

}


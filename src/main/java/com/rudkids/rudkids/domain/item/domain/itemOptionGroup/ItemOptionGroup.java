package com.rudkids.rudkids.domain.item.domain.itemOptionGroup;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.common.AbstractEntity;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.itemOption.ItemOption;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Entity
@Table(name = "item_option_group")
public class ItemOptionGroup extends AbstractEntity {

    @Id
    @Column(name = "item_option_group_id", columnDefinition = "BINARY(16)")
    private final UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    Item item;

    private Integer ordering;
    @Embedded
    private ItemOptionGroupName itemOptionGroupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemOptionGroup", cascade = CascadeType.PERSIST)
    private final List<ItemOption> itemOptions = new ArrayList<>();

    protected ItemOptionGroup() {
    }

    @Builder
    public ItemOptionGroup(Item item, Integer ordering, ItemOptionGroupName itemOptionGroupName) {
        this.item = item;
        this.ordering = ordering;
        this.itemOptionGroupName = itemOptionGroupName;
    }
}

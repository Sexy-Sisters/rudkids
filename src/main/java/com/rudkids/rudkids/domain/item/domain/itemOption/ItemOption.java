package com.rudkids.rudkids.domain.item.domain.itemOption;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.common.AbstractEntity;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroup;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@Getter
@Entity
@Table(name = "item_option")
public class ItemOption extends AbstractEntity {

    @Id
    @Column(name = "item_option_id", columnDefinition = "BINARY(16)")
    private final UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_option_group_id")
    private ItemOptionGroup itemOptionGroup;

    private Integer ordering;

    @Embedded
    private ItemOptionName itemOptionName;

    @Embedded
    private ItemOptionPrice itemOptionPrice;

    public ItemOption() {
    }

    @Builder
    public ItemOption(ItemOptionGroup itemOptionGroup, Integer ordering, ItemOptionName itemOptionName, ItemOptionPrice itemOptionPrice) {
        this.itemOptionGroup = itemOptionGroup;
        this.ordering = ordering;
        this.itemOptionName = itemOptionName;
        this.itemOptionPrice = itemOptionPrice;
    }
}

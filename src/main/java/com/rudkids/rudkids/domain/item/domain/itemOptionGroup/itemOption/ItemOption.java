package com.rudkids.rudkids.domain.item.domain.itemOptionGroup.itemOption;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@Entity
@Table(name = "item_option")
public class ItemOption extends AbstractEntity {

    @Id
    @Column(name = "item_option_id", columnDefinition = "BINARY(16)")
    private final UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_option_id")
    private ItemOption itemOption;

    private Integer ordering;
    @Embedded
    private ItemOptionName itemOptionName;
    public ItemOption() {
    }
    @Builder
    public ItemOption(ItemOption itemOption, Integer ordering, ItemOptionName itemOptionName) {
        this.itemOption = itemOption;
        this.ordering = ordering;
        this.itemOptionName = itemOptionName;
    }
}

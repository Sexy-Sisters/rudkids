package com.rudkids.rudkids.domain.item.domain.itemOption;

import com.rudkids.rudkids.common.AbstractEntity;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroup;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Slf4j
@Entity
@Table(name = "tbl_item_option")
public class ItemOption extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "item_option_id")
    private UUID id;

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

    public Integer getOrdering() {
        return ordering;
    }

    public String getItemOptionName() {
        return itemOptionName.getValue();
    }

    public int getItemOptionPrice() {
        return itemOptionPrice.getValue();
    }
}

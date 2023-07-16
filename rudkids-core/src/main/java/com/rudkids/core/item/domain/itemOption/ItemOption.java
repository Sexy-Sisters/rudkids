package com.rudkids.core.item.domain.itemOption;

import com.rudkids.core.common.domain.AbstractEntity;
import com.rudkids.core.item.domain.itemOptionGroup.ItemOptionGroup;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "tbl_item_option")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemOption extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "item_option_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_option_group_id")
    private ItemOptionGroup itemOptionGroup;

    @Embedded
    private ItemOptionName itemOptionName;

    @Embedded
    private ItemOptionPrice itemOptionPrice;

    private int ordering;

    @Builder
    public ItemOption(
        ItemOptionGroup itemOptionGroup,
        ItemOptionName itemOptionName,
        ItemOptionPrice itemOptionPrice,
        int ordering
    ) {
        this.itemOptionGroup = itemOptionGroup;
        this.itemOptionName = itemOptionName;
        this.itemOptionPrice = itemOptionPrice;
        this.ordering = ordering;
    }

    public String getItemOptionName() {
        return itemOptionName.getValue();
    }

    public int getItemOptionPrice() {
        return itemOptionPrice.getValue();
    }

    public int getOrdering() {
        return ordering;
    }
}

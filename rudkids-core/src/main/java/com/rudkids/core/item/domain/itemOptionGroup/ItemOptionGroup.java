package com.rudkids.core.item.domain.itemOptionGroup;

import com.rudkids.core.common.domain.AbstractEntity;
import com.rudkids.core.item.domain.Item;
import com.rudkids.core.item.domain.itemOption.ItemOption;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Entity
@Table(name = "tbl_item_option_group")
public class ItemOptionGroup extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "item_option_group_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer ordering;
    @Embedded
    private ItemOptionGroupName itemOptionGroupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemOptionGroup", cascade = CascadeType.ALL)
    private final List<ItemOption> itemOptions = new ArrayList<>();

    protected ItemOptionGroup() {
    }

    @Builder
    public ItemOptionGroup(Item item, Integer ordering, ItemOptionGroupName itemOptionGroupName) {
        this.item = item;
        item.addOptionGroup(this);
        this.ordering = ordering;
        this.itemOptionGroupName = itemOptionGroupName;
    }

    public void addItemOption(ItemOption itemOption) {
        itemOptions.add(itemOption);
    }

    public Integer getOrdering() {
        return ordering;
    }

    public String getItemOptionGroupName() {
        return itemOptionGroupName.getValue();
    }

    public List<ItemOption> getItemOptions() {
        return itemOptions;
    }
}

package com.rudkids.rudkids.domain.item.domain.itemOptionGroup;

import com.rudkids.rudkids.common.AbstractEntity;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.itemOption.ItemOption;
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

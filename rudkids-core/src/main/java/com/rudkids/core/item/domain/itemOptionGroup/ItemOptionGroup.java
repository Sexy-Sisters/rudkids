package com.rudkids.core.item.domain.itemOptionGroup;

import com.rudkids.core.common.domain.AbstractEntity;
import com.rudkids.core.item.domain.Item;
import com.rudkids.core.item.domain.itemOption.ItemOption;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_item_option_group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemOptionGroup extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "item_option_group_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Embedded
    private ItemOptionGroupName itemOptionGroupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemOptionGroup", cascade = CascadeType.ALL)
    private final List<ItemOption> itemOptions = new ArrayList<>();

    @Builder
    public ItemOptionGroup(Item item, ItemOptionGroupName itemOptionGroupName) {
        this.item = item;
        item.addOptionGroup(this);
        this.itemOptionGroupName = itemOptionGroupName;
    }

    public void addItemOption(ItemOption itemOption) {
        itemOptions.add(itemOption);
    }

    public String getItemOptionGroupName() {
        return itemOptionGroupName.getValue();
    }

    public List<ItemOption> getItemOptions() {
        return itemOptions;
    }
}

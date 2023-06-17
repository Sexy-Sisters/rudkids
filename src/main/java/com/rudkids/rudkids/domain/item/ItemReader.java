package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.Item;

import java.util.List;
import java.util.UUID;

public interface ItemReader {
    Item getItem(UUID id);
    List<ItemInfo.ItemOptionGroupInfo> getItemOptionSeries(Item item);
}

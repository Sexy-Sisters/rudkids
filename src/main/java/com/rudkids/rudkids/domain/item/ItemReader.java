package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.Item;

public interface ItemReader {
    Item getItem(String name);
}

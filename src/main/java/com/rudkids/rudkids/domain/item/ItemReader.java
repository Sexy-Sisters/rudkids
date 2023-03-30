package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.product.domain.Product;

import java.util.List;
import java.util.UUID;

public interface ItemReader {
    Item getItem(UUID id);
    Item getItem(String name);
}

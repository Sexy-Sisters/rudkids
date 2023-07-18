package com.rudkids.core.admin.service;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.item.domain.Item;
import com.rudkids.core.product.domain.Product;

public interface ItemFactory {
    Item create(Product product, AdminRequest.CreateItem request);
    void update(Item item, AdminRequest.UpdateItem request);
}
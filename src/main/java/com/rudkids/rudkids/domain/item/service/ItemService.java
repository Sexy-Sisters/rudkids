package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.ItemCommand;

public interface ItemService {
    void registerItem(ItemCommand.CreateRequest command);
}

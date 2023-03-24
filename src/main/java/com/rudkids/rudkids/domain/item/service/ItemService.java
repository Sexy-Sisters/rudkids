package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemInfo;

import java.util.List;
import java.util.UUID;

public interface ItemService {
    void registerItem(ItemCommand.RegisterRequest command);
    List<ItemInfo.Main> findItems(UUID productId);
    ItemInfo.Detail findItemDetail(UUID id);
}

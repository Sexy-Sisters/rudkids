package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemInfo;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;

import java.util.UUID;

public interface ItemService {
    void create(ItemCommand.RegisterItemRequest command, UUID productId, UUID userId);
    ItemInfo.Detail find(UUID itemId);
    ItemStatus changeItemStatus(UUID itemId, ItemStatus userId, UUID itemStatus);
}

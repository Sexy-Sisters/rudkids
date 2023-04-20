package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemInfo;

import java.util.UUID;

public interface ItemService {
    void create(ItemCommand.RegisterItemRequest command, UUID productId, UUID userId);
    ItemInfo.Detail find(UUID id);
    String changeOnSales(UUID id);
    String changeEndOfSales(UUID id);
    String changePrepare(UUID id);
}

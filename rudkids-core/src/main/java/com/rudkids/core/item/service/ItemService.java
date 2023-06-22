package com.rudkids.core.item.service;

import com.rudkids.core.item.dto.ItemRequest;
import com.rudkids.core.item.dto.ItemResponse;

import java.util.UUID;

public interface ItemService {
    UUID create(UUID userId, UUID productId, ItemRequest.Create request);
    ItemResponse.Detail get(UUID itemId);
    String changeStatus(UUID userId, UUID itemId, ItemRequest.ChangeStatus request);
    void update(UUID userId, UUID itemId, ItemRequest.Update request);
    void delete(UUID userId, UUID itemId);
}
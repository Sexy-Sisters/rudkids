package com.rudkids.core.item.service;

import com.rudkids.core.item.dto.ItemRequest;
import com.rudkids.core.item.dto.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ItemService {
    UUID create(UUID userId, UUID productId, ItemRequest.Create request);
    ItemResponse.Detail get(String name);
    Page<ItemResponse.Main> getPopularItems(Pageable pageable);
    Page<ItemResponse.VideoImage> getItemVideoImages(Pageable pageable);
    ItemResponse.Video getItemVideo(String name);
    String changeStatus(UUID userId, UUID itemId, ItemRequest.ChangeStatus request);
    void update(UUID userId, UUID itemId, ItemRequest.Update request);
    void delete(UUID userId, UUID itemId);
}
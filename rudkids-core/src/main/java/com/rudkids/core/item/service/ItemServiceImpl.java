package com.rudkids.core.item.service;

import com.rudkids.core.item.domain.*;
import com.rudkids.core.item.dto.ItemRequest;
import com.rudkids.core.item.dto.ItemResponse;
import com.rudkids.core.product.domain.ProductRepository;
import com.rudkids.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;
    private final ItemFactory itemFactory;

    @Override
    public UUID create(UUID userId, UUID productId, ItemRequest.Create request) {
        var user = userRepository.getUser(userId);
        user.validateAdminOrPartnerRole();
        var item = itemFactory.create(request);
        itemRepository.save(item);

        var product = productRepository.get(productId);
        item.setProduct(product);
        return item.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public ItemResponse.Detail get(String name) {
        var item = itemRepository.getByEnNme(name);
        return new ItemResponse.Detail(item);
    }

    @Override
    public Page<ItemResponse.Main> getPopularItems(Pageable pageable) {
        return itemRepository.getPopularItems(pageable)
            .map(ItemResponse.Main::new);
    }

    @Override
    public Page<ItemResponse.VideoImage> getItemVideoImages(Pageable pageable) {
        return itemRepository.getAll(pageable)
            .map(ItemResponse.VideoImage::new);
    }

    @Override
    public ItemResponse.Video getItemVideo(String name) {
        var item = itemRepository.getByEnNme(name);
        return new ItemResponse.Video(item);
    }

    @Override
    public void update(UUID userId, UUID itemId, ItemRequest.Update request) {
        var user = userRepository.getUser(userId);
        user.validateAdminOrPartnerRole();
        var item = itemRepository.get(itemId);
        item.deleteItemImage();

        itemFactory.update(item, request);
    }

    @Override
    public String changeStatus(UUID userId, UUID itemId, ItemRequest.ChangeStatus request) {
        var user = userRepository.getUser(userId);
        user.validateAdminOrPartnerRole();
        var item = itemRepository.get(itemId);

        var status = ItemStatus.toEnum(request.itemStatus());
        item.changeStatus(status);
        return item.getItemStatus().name();
    }

    @Override
    public void delete(UUID itemId, UUID userId) {
        var user = userRepository.getUser(userId);
        user.validateAdminOrPartnerRole();
        var item = itemRepository.get(itemId);
        item.deleteItemImage();
        itemRepository.delete(item);
    }
}

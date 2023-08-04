package com.rudkids.core.collection.service;

import com.rudkids.core.collection.domain.CollectionItemCategory;
import com.rudkids.core.collection.domain.CollectionItemRepository;
import com.rudkids.core.collection.dto.CollectionItemResponse;
import com.rudkids.core.item.domain.Item;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CollectionService {
    private final UserRepository userRepository;
    private final CollectionItemRepository collectionItemRepository;

    public List<CollectionItemResponse.Info> getAll(UUID userId, String category) {
        var user = userRepository.getUser(userId);
        var collectionItems = collectionItemRepository.getByCategory(CollectionItemCategory.toEnum(category));

        return collectionItems.stream()
            .map(item -> {
                if(isBoughtItem(user, item.getItemEnName())) {
                    return new CollectionItemResponse.Info(item.getItemEnName(), item.getItemImageUrl());
                }
                return new CollectionItemResponse.Info("?", item.getItemGrayImageUrl());
            })
            .toList();
    }

    private boolean isBoughtItem(User user, String itemName) {
        return user.getBoughtItems().stream()
            .map(Item::getMysteryItemName)
            .anyMatch(name -> name.equals(itemName));
    }
}

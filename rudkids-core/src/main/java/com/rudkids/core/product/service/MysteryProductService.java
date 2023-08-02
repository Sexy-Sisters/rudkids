package com.rudkids.core.product.service;

import com.rudkids.core.item.domain.Item;
import com.rudkids.core.item.dto.ItemResponse;
import com.rudkids.core.product.domain.MysteryProduct;
import com.rudkids.core.product.domain.MysteryProductRepository;
import com.rudkids.core.product.dto.ProductResponse;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MysteryProductService {
    private final UserRepository userRepository;
    private final MysteryProductRepository mysteryProductRepository;

    public ProductResponse.Main get(UUID mysteryProductId) {
        var mysteryProduct = mysteryProductRepository.get(mysteryProductId);
        return new ProductResponse.Main(mysteryProduct);
    }

    public ProductResponse.Detail getDetail(UUID userId, UUID mysteryProductId) {
        var mysteryProduct = mysteryProductRepository.get(mysteryProductId);
        var user = userRepository.getUser(userId);

        var items = getItems(mysteryProduct, user);
        return new ProductResponse.Detail(mysteryProduct, items);
    }

    private Page<ItemResponse.Main> getItems(MysteryProduct mysteryProduct, User user) {
        return mysteryProduct.getItems().stream()
            .map(item -> getItem(user, item))
            .collect(collectingAndThen(toList(), PageImpl::new));
    }

    private ItemResponse.Main getItem(User user, Item item) {
        boolean isBoughtMysteryItem = isBoughtMysteryItem(user, item);
        if(isBoughtMysteryItem) {
            return new ItemResponse.Main(item);
        }

        return new ItemResponse.Main(
            item.getId(),
            "?",
            999999,
            item.getFirstImageUrl(),
            item.getItemStatus());
    }

    private boolean isBoughtMysteryItem(User user, Item item) {
        return user.getBoughtItems().stream()
            .map(Item::getMysteryItemName)
            .anyMatch(name -> name.equals(item.getEnName()));
    }
}

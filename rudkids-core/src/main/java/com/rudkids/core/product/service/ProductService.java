package com.rudkids.core.product.service;

import com.rudkids.core.item.domain.Item;
import com.rudkids.core.item.domain.ItemRepository;
import com.rudkids.core.item.dto.ItemResponse;
import com.rudkids.core.product.domain.Product;
import com.rudkids.core.product.domain.ProductRepository;
import com.rudkids.core.product.dto.ProductResponse;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    public Page<ProductResponse.Main> getAll(Pageable pageable) {
        return productRepository.getAll(pageable)
            .map(ProductResponse.Main::new);
    }

    public ProductResponse.Detail get(UUID userId, UUID productId, Pageable pageable) {
        var product = productRepository.get(productId);
        var user = userRepository.getUser(userId);

        if(product.isMystery()) {
            var mysteryItems = getItems(product, user);
            return new ProductResponse.Detail(product, mysteryItems);
        }
        var items = itemRepository.get(product, pageable)
            .map(ItemResponse.Main::new);

        return new ProductResponse.Detail(product, items);
    }

    private Page<ItemResponse.Main> getItems(Product product, User user) {
        return product.getItems().stream()
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

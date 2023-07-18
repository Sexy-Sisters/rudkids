package com.rudkids.core.product.service;

import com.rudkids.core.image.dto.ImageResponse;
import com.rudkids.core.item.domain.ItemRepository;
import com.rudkids.core.item.dto.ItemResponse;
import com.rudkids.core.product.domain.ProductBannerImage;
import com.rudkids.core.product.domain.ProductRepository;
import com.rudkids.core.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    public Page<ProductResponse.Main> getAll(Pageable pageable) {
        return productRepository.getAll(pageable)
            .map(ProductResponse.Main::new);
    }

    public ProductResponse.Detail get(UUID productId, Pageable pageable) {
        var product = productRepository.get(productId);
        var items = itemRepository.get(product, pageable)
            .map(ItemResponse.Main::new);

        var bannerImages = product.getProductBannerImages().stream()
            .sorted(Comparator.comparing(ProductBannerImage::getOrdering))
            .map(ImageResponse.Info::new)
            .toList();

        return new ProductResponse.Detail(product, items, bannerImages);
    }
}

package com.rudkids.core.product.service;

import com.rudkids.core.image.dto.ImageResponse;
import com.rudkids.core.image.service.ImageDeletedEvent;
import com.rudkids.core.item.domain.ItemRepository;
import com.rudkids.core.item.dto.ItemResponse;
import com.rudkids.core.product.domain.*;
import com.rudkids.core.product.dto.ProductRequest;
import com.rudkids.core.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;
    private final ProductFactory productFactory;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public UUID create(ProductRequest.Create request) {
        var product = productFactory.create(request);
        productRepository.save(product);
        return product.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponse.Main> getAll(Pageable pageable) {
        return productRepository.getAll(pageable)
            .map(ProductResponse.Main::new);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse.Detail get(UUID productId, Pageable pageable) {
        var product = productRepository.get(productId);
        var items = itemRepository.get(product, pageable)
            .map(ItemResponse.Main::new);

        var bannerImages = product.getProductBannerImages().stream()
            .sorted(Comparator.comparing(ProductBannerImage::getOrdering))
            .map(ImageResponse.Info::new)
            .toList();

        return ProductResponse.Detail.builder()
            .title(product.getTitle())
            .bio(product.getProductBio())
            .frontImage(new ImageResponse.Info(product.getFrontImagePath(), product.getFrontImageUrl()))
            .backImage(new ImageResponse.Info(product.getBackImagePath(), product.getBackImageUrl()))
            .bannerImages(bannerImages)
            .items(items)
            .build();
    }

    @Override
    public void update(UUID productId, ProductRequest.Update request) {
        var product = productRepository.get(productId);
        productFactory.update(product, request);

        eventPublisher.publishEvent(new ImageDeletedEvent(product.getFrontImagePath()));
        eventPublisher.publishEvent(new ImageDeletedEvent(product.getBackImagePath()));
    }

    @Override
    public void changeStatus(UUID productId, String status) {
        var product = productRepository.get(productId);
        var productStatus = ProductStatus.toEnum(status);
        product.changeStatus(productStatus);
    }

    @Override
    public void delete(UUID productId) {
        var product = productRepository.get(productId);
        productRepository.delete(product);
        deleteImage(product);
    }

    private void deleteImage(Product product) {
        eventPublisher.publishEvent(new ImageDeletedEvent(product.getBackImagePath()));
        eventPublisher.publishEvent(new ImageDeletedEvent(product.getFrontImagePath()));

        for(String path: product.getBannerPaths()) {
            eventPublisher.publishEvent(new ImageDeletedEvent(path));
        }
    }
}

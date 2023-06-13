package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.domain.product.*;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import com.rudkids.rudkids.domain.product.exception.ProductStatusNotFoundException;
import com.rudkids.rudkids.domain.product.service.strategy.productStatus.ChangeProductStatusStrategy;
import com.rudkids.rudkids.infrastructure.item.ItemRepository;
import com.rudkids.rudkids.infrastructure.product.ProductRepository;
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
public class ProductServiceImpl implements ProductService {
    private final ProductStore productStore;
    private final ProductReader productReader;
    private final ProductMapper productMapper;
    private final ProductFactory productFactory;
    private final List<ChangeProductStatusStrategy> changeProductStatusStrategies;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    @Override
    public void create(ProductCommand.CreateRequest command) {
        var initProduct = productFactory.create(command);
        productStore.store(initProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductInfo.Main> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
            .map(productMapper::toMain);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductInfo.Detail find(UUID productId, Pageable pageable) {
        var product = productReader.getProduct(productId);
        var itemInfo = itemRepository.findByProduct(product, pageable)
            .map(productMapper::toInfo);

        return ProductInfo.Detail.builder()
            .title(product.getTitle())
            .bio(product.getProductBio())
            .frontImageUrl(product.getFrontImageUrl())
            .backImageUrl(product.getBackImageUrl())
            .bannerImageUrls(product.getBannerUrls())
            .items(itemInfo)
            .build();
    }

    @Override
    public List<ProductInfo.Search> search(String title) {
        return productReader.getProducts(title).stream()
            .map(productMapper::toSearch)
            .toList();
    }

    @Override
    public void update(ProductCommand.UpdateRequest command, UUID productId) {
        var product = productReader.getProduct(productId);
        productFactory.update(product, command);
    }

    @Override
    public void changeStatus(ProductStatus productStatus, UUID productId) {
        var product = productReader.getProduct(productId);
        var foundStrategy = findChangeStatusStrategy(productStatus);
        foundStrategy.changeStatus(product);
    }

    @Override
    public void delete(UUID productId) {
        var product = productReader.getProduct(productId);
        productStore.delete(product);
    }

    public ChangeProductStatusStrategy findChangeStatusStrategy(ProductStatus productStatus) {
        return changeProductStatusStrategies.stream()
            .filter(strategy -> strategy.isProductStatus(productStatus))
            .findFirst()
            .orElseThrow(ProductStatusNotFoundException::new);
    }
}

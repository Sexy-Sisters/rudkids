package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.domain.image.service.ImageService;
import com.rudkids.rudkids.domain.product.*;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import com.rudkids.rudkids.domain.product.exception.ProductStatusNotFoundException;
import com.rudkids.rudkids.domain.product.service.strategy.productStatus.ChangeProductStatusStrategy;
import lombok.RequiredArgsConstructor;
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
    private final ImageService imageService;

    @Override
    public void create(ProductCommand.CreateRequest command) {
        var initProduct = productFactory.create(command);
        productStore.store(initProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInfo.Main> findAll() {
        return productReader.getProducts().stream()
            .map(productMapper::toMain)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductInfo.Detail find(UUID productId) {
        var product = productReader.getProduct(productId);
        var items = product.getItems().stream()
            .map(productMapper::toInfo)
            .toList();
        return ProductInfo.Detail.builder()
            .title(product.getTitle())
            .bio(product.getProductBio())
            .frontImageUrl(product.getFrontImageUrl())
            .backImageUrl(product.getBackImageUrl())
            .items(items)
            .build();
    }

    @Override
    public List<ProductInfo.Search> search(String title) {
        return productReader.getProducts(title).stream()
            .map(productMapper::toInfo)
            .toList();
    }

    @Override
    public void update(ProductCommand.UpdateRequest command, UUID productId) {
        var product = productReader.getProduct(productId);
        imageService.delete(product);
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
        imageService.delete(product);
        productStore.delete(product);
    }

    public ChangeProductStatusStrategy findChangeStatusStrategy(ProductStatus productStatus) {
        return changeProductStatusStrategies.stream()
            .filter(strategy -> strategy.isProductStatus(productStatus))
            .findFirst()
            .orElseThrow(ProductStatusNotFoundException::new);
    }
}

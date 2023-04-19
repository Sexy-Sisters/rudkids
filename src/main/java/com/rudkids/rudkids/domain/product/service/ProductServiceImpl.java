package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.domain.product.*;
import com.rudkids.rudkids.domain.product.domain.ProductBio;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import com.rudkids.rudkids.domain.product.domain.Title;
import com.rudkids.rudkids.domain.user.UserReader;
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
    private final UserReader userReader;

    @Override
    public void create(ProductCommand.RegisterRequest command, UUID userId) {
        var user = userReader.getUser(userId);
        user.validateAdminRole();
        var title = Title.create(command.title());
        var productBio = ProductBio.create(command.productBio());
        var initProduct = Product.create(title, productBio);
        productStore.store(initProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInfo.Main> findAll() {
        return productReader.getProducts().stream()
            .map(productMapper::of)
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
            .items(items)
            .build();
    }

    @Override
    public ProductStatus closeProduct(UUID productId, UUID userId) {
        var user = userReader.getUser(userId);
        user.validateAdminRole();
        var product = productReader.getProduct(productId);
        return product.close();
    }

    @Override
    public ProductStatus openProduct(UUID productId, UUID userId) {
        var user = userReader.getUser(userId);
        user.validateAdminRole();
        var product = productReader.getProduct(productId);
        return product.open();
    }
}

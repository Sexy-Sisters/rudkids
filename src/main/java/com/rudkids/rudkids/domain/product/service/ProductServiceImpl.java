package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.domain.item.ItemInfo;
import com.rudkids.rudkids.domain.item.ItemMapper;
import com.rudkids.rudkids.domain.product.*;
import com.rudkids.rudkids.domain.product.domain.ProductBio;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.product.domain.Title;
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

    @Override
    public void create(ProductCommand.RegisterRequest command) {
        var title = Title.create(command.title());
        var bio = ProductBio.create(command.productBio());

        var initProduct = Product.builder()
            .title(title)
            .productBio(bio)
            .build();
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
    public void closeProduct(UUID productId) {
        var product = productReader.getProduct(productId);
        product.close();
    }

    @Override
    public void openProduct(UUID productId) {
        var product = productReader.getProduct(productId);
        product.open();
    }
}

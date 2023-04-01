package com.rudkids.rudkids.domain.product.service;

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
    public void registerProduct(ProductCommand.RegisterRequest command) {
        Title title = Title.create(command.getTitle());
        ProductBio bio = ProductBio.create(command.getBio());

        Product initProduct = Product.builder()
            .title(title)
            .productBio(bio)
            .build();
        productStore.store(initProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInfo.Main> findProduct() {
        return productReader.getProducts().stream()
            .map(productMapper::of)
            .toList();
    }

    @Override
    public void closeProduct(UUID productId) {
        Product product = productReader.getProduct(productId);
        product.close();
    }

    @Override
    public void openProduct(UUID productId) {
        Product product = productReader.getProduct(productId);
        product.open();
    }
}

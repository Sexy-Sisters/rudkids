package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.domain.product.*;
import com.rudkids.rudkids.infrastructure.product.ProductStoreImpl;
import com.rudkids.rudkids.domain.product.domain.Bio;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.product.domain.Title;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductStore productStore;
    private final ProductReader productReader;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public void registerProduct(ProductCommand.RegisterRequest command) {
        Title title = Title.create(command.getTitle());
        Bio bio = Bio.create(command.getBio());

        Product initProduct = Product.builder()
            .title(title)
            .bio(bio)
            .build();
        productStore.store(initProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInfo.Main> findProduct() {
        return productReader.getProducts().stream()
            .map(productMapper::toEntity)
            .toList();
    }
}

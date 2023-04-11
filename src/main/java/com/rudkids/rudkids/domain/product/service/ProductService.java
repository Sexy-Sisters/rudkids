package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.ProductInfo;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void registerProduct(ProductCommand.RegisterRequest command);
    List<ProductInfo.Main> findProducts();
    ProductInfo.Detail findProduct(UUID productId);
    void closeProduct(UUID productId);
    void openProduct(UUID productId);
}

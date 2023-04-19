package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.ProductInfo;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void create(ProductCommand.RegisterRequest command, UUID userId);
    List<ProductInfo.Main> findAll();
    ProductInfo.Detail find(UUID productId);
    ProductStatus closeProduct(UUID productId, UUID userId);
    ProductStatus openProduct(UUID productId, UUID userId);
}

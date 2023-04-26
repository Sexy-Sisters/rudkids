package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.ProductInfo;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    void create(ProductCommand.CreateRequest command, UUID userId);

    List<ProductInfo.Main> findAll();

    ProductInfo.Detail find(UUID productId);

    void changeStatus(ProductStatus productStatus, UUID productId, UUID userId);

    void update(ProductCommand.UpdateRequest command, UUID productId, UUID userId);

    void delete(UUID productId, UUID userId);
}

package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.ProductInfo;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    void create(ProductCommand.CreateRequest command);

    List<ProductInfo.Main> findAll();

    ProductInfo.Detail find(UUID productId);
    List<ProductInfo.Search> search(String title);

    void changeStatus(ProductStatus productStatus, UUID productId);

    void update(ProductCommand.UpdateRequest command, UUID productId);

    void delete(UUID productId);
}

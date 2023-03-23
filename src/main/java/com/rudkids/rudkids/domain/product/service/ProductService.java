package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.ProductInfo;

import java.util.List;

public interface ProductService {
    void registerProduct(ProductCommand.RegisterRequest command);
    List<ProductInfo.Main> findProduct();
}

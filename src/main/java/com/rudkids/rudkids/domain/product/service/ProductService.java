package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.domain.product.ProductCommand;

public interface ProductService {
    void registerProduct(ProductCommand.RegisterRequest command);
}

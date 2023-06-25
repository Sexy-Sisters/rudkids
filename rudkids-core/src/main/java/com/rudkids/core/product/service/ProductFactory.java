package com.rudkids.core.product.service;

import com.rudkids.core.product.domain.Product;
import com.rudkids.core.product.dto.ProductRequest;

public interface ProductFactory {
    Product create(ProductRequest.Create request);
    void update(Product product, ProductRequest.Update request);
}

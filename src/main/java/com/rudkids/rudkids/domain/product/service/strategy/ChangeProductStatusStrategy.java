package com.rudkids.rudkids.domain.product.service.strategy;

import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;

public interface ChangeProductStatusStrategy {
    boolean isProductStatus(ProductStatus productStatus);
    void changeStatus(Product product);
}

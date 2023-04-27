package com.rudkids.rudkids.domain.product;

import com.rudkids.rudkids.domain.product.domain.Product;

public interface ProductFactory {
    Product create(ProductCommand.CreateRequest command);

    void update(Product product, ProductCommand.UpdateRequest command);
}

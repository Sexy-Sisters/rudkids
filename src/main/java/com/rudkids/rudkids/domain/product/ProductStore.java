package com.rudkids.rudkids.domain.product;

import com.rudkids.rudkids.domain.product.domain.Product;

import java.util.UUID;

public interface ProductStore {
    void store(Product product);

    void delete(UUID productId);
}

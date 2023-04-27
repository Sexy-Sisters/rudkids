package com.rudkids.rudkids.domain.product;

import com.rudkids.rudkids.domain.product.domain.Product;

import java.util.List;
import java.util.UUID;

public interface ProductReader {
    Product getProduct(UUID id);
    Product getProduct(String title);
    List<Product> getProducts();
    List<Product> getProducts(String title);
}

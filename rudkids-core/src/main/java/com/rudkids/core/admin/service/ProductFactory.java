package com.rudkids.core.admin.service;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.product.domain.Product;

public interface ProductFactory {
    Product create(AdminRequest.CreateProduct request);
    void update(Product product, AdminRequest.UpdateProduct request);
}

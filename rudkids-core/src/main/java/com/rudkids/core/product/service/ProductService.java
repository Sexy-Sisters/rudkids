package com.rudkids.core.product.service;

import com.rudkids.core.product.dto.ProductRequest;
import com.rudkids.core.product.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    UUID create(ProductRequest.Create request);
    Page<ProductResponse.Main> getAll(Pageable pageable);
    ProductResponse.Detail get(UUID productId, Pageable pageable);
    List<ProductResponse.Search> search(String title);
    void update(UUID productId, ProductRequest.Update request);
    void changeStatus(UUID productId, String status);
    void delete(UUID productId);
}

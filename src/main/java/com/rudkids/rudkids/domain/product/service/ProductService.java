package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.ProductInfo;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    void create(ProductCommand.CreateRequest command);

    Page<ProductInfo.Main> findAll(Pageable pageable);

    ProductInfo.Detail find(UUID productId, Pageable pageable);
    List<ProductInfo.Search> search(String title);

    void changeStatus(ProductStatus productStatus, UUID productId);

    void update(ProductCommand.UpdateRequest command, UUID productId);

    void delete(UUID productId);
}

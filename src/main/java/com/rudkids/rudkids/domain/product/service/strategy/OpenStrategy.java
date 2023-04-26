package com.rudkids.rudkids.domain.product.service.strategy;

import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import org.springframework.stereotype.Component;

@Component
public class OpenStrategy implements ChangeProductStatusStrategy {
    @Override
    public boolean isProductStatus(ProductStatus productStatus) {
        return ProductStatus.OPEN == productStatus;
    }

    @Override
    public void changeStatus(Product product) {
        product.open();
    }
}

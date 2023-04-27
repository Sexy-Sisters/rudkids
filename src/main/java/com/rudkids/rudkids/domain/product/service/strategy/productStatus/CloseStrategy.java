package com.rudkids.rudkids.domain.product.service.strategy.productStatus;

import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import org.springframework.stereotype.Component;

@Component
public class CloseStrategy implements ChangeProductStatusStrategy {
    @Override
    public boolean isProductStatus(ProductStatus productStatus) {
        return ProductStatus.CLOSED == productStatus;
    }

    @Override
    public void changeStatus(Product product) {
        product.close();
    }
}

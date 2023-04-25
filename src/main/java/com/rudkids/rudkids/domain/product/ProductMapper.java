package com.rudkids.rudkids.domain.product;

import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.product.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductInfo.Main of(Product product) {
        return ProductInfo.Main.builder()
            .productId(product.getId())
            .title(product.getTitle())
            .productBio(product.getProductBio())
            .build();
    }


    public ProductInfo.ProductItem toInfo(Item item) {
        return ProductInfo.ProductItem.builder()
            .itemId(item.getId())
            .name(item.getName())
            .price(item.getPrice())
            .itemStatus(item.getItemStatus())
            .build();
    }

}

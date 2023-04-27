package com.rudkids.rudkids.infrastructure.product;

import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.ProductFactory;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.product.domain.ProductBio;
import com.rudkids.rudkids.domain.product.domain.Title;
import org.springframework.stereotype.Component;

@Component
public class ProductFactoryImpl implements ProductFactory {

    @Override
    public Product create(ProductCommand.CreateRequest command) {
        var title = Title.create(command.title());
        var productBio = ProductBio.create(command.productBio());
        return Product.create(title, productBio);
    }
}

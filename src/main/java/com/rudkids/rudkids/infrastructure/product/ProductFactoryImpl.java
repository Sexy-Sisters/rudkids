package com.rudkids.rudkids.infrastructure.product;

import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.ProductFactory;
import com.rudkids.rudkids.domain.product.domain.*;
import org.springframework.stereotype.Component;

@Component
public class ProductFactoryImpl implements ProductFactory {

    @Override
    public Product create(ProductCommand.CreateRequest command) {
        var title = Title.create(command.title());
        var productBio = ProductBio.create(command.productBio());
        var frontImage = command.frontImage();
        var productFrontImage = ProductFrontImage.create(frontImage.path(), frontImage.url());
        var backImage = command.backImage();
        var productBackImage = ProductBackImage.create(backImage.path(), backImage.url());
        return Product.create(
            title,
            productBio,
            productFrontImage,
            productBackImage
        );
    }
}

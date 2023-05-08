package com.rudkids.rudkids.infrastructure.product;

import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.ProductFactory;
import com.rudkids.rudkids.domain.product.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductFactoryImpl implements ProductFactory {

    @Override
    public Product create(ProductCommand.CreateRequest command) {
        var title = Title.create(command.title());
        var productBio = ProductBio.create(command.productBio());
        var productFrontImage = ProductFrontImage.create(
            command.frontImage().path(),
            command.frontImage().url()
        );
        var productBackImage = ProductBackImage.create(
            command.backImage().path(),
            command.backImage().url()
        );
        return Product.create(title, productBio, productFrontImage, productBackImage);
    }

    @Override
    public void update(Product product, ProductCommand.UpdateRequest command) {
        var title = Title.create(command.title());
        var productBio = ProductBio.create(command.productBio());
        var frontImage = ProductFrontImage.create(
            command.frontImage().path(),
            command.frontImage().url()
        );
        var backImage = ProductBackImage.create(
            command.backImage().path(),
            command.backImage().url()
        );
        product.update(title, productBio, frontImage, backImage);
    }
}

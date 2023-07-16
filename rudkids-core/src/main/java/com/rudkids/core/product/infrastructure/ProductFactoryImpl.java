package com.rudkids.core.product.infrastructure;

import com.rudkids.core.product.domain.*;
import com.rudkids.core.product.dto.ProductRequest;
import com.rudkids.core.product.service.ProductFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductFactoryImpl implements ProductFactory {

    @Override
    public Product create(ProductRequest.Create request) {
        var product = generateProduct(request);
        generateProductBannerImages(product, request);
        return product;
    }

    private Product generateProduct(ProductRequest.Create request) {
        var title = Title.create(request.title());
        var bio = ProductBio.create(request.productBio());
        var frontImage = ProductFrontImage.create(request.frontImage().path(), request.frontImage().url());
        var backImage = ProductBackImage.create(request.backImage().path(), request.backImage().url());

        return Product.builder()
            .title(title)
            .productBio(bio)
            .frontImage(frontImage)
            .backImage(backImage)
            .build();
    }

    private void generateProductBannerImages(Product product, ProductRequest.Create request) {
        for(ProductRequest.CreateBannerImage image: request.bannerImages()) {
            var bannerImage = ProductBannerImage.create(
                product,
                image.image().path(),
                image.image().url(),
                image.ordering());
            product.addBannerImage(bannerImage);
        }
    }

    @Override
    public void update(Product product, ProductRequest.Update request) {
        var title = Title.create(request.title());
        var bio = ProductBio.create(request.productBio());
        var frontImage = ProductFrontImage.create(request.frontImage().path(), request.frontImage().url());
        var backImage = ProductBackImage.create(request.backImage().path(), request.backImage().url());

        product.update(title, bio, frontImage, backImage);
    }
}

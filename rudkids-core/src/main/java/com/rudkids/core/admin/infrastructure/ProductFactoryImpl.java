package com.rudkids.core.admin.infrastructure;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.product.domain.*;
import com.rudkids.core.admin.service.ProductFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductFactoryImpl implements ProductFactory {

    @Override
    public Product create(AdminRequest.CreateProduct request) {
        var product = generateProduct(request);
        generateProductBannerImages(product, request);
        return product;
    }

    private Product generateProduct(AdminRequest.CreateProduct request) {
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

    private void generateProductBannerImages(Product product, AdminRequest.CreateProduct request) {
        for(AdminRequest.CreateBannerImage image: request.bannerImages()) {
            var bannerImage = ProductBannerImage.create(
                product,
                image.path(),
                image.url(),
                image.ordering());
            product.addBannerImage(bannerImage);
        }
    }

    @Override
    public void update(Product product, AdminRequest.UpdateProduct request) {
        var title = Title.create(request.title());
        var bio = ProductBio.create(request.productBio());
        var frontImage = ProductFrontImage.create(request.frontImage().path(), request.frontImage().url());
        var backImage = ProductBackImage.create(request.backImage().path(), request.backImage().url());

        product.update(title, bio, frontImage, backImage);
    }
}

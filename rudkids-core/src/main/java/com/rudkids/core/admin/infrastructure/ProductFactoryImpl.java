package com.rudkids.core.admin.infrastructure;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.product.domain.*;
import com.rudkids.core.admin.service.ProductFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductFactoryImpl implements ProductFactory {

    @Override
    public Product create(AdminRequest.CreateProduct request) {
        var product = generateProduct(request);
        saveProductBannerImages(product, request.bannerImages());
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

    @Override
    public void update(Product product, AdminRequest.UpdateProduct request) {
        var title = Title.create(request.title());
        var bio = ProductBio.create(request.productBio());
        var frontImage = ProductFrontImage.create(request.frontImage().path(), request.frontImage().url());
        var backImage = ProductBackImage.create(request.backImage().path(), request.backImage().url());

        product.update(title, bio, frontImage, backImage);
        saveProductBannerImages(product, request.bannerImages());
    }

    private void saveProductBannerImages(Product product, List<AdminRequest.BannerImage> images) {
        for(AdminRequest.BannerImage image: images) {
            var bannerImage = ProductBannerImage.create(
                product,
                image.path(),
                image.url(),
                image.ordering());
            product.addBannerImage(bannerImage);
        }
    }
}

package com.rudkids.core.admin.infrastructure;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.product.domain.*;
import com.rudkids.core.admin.service.ProductFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductFactoryImpl implements ProductFactory {

    @Override
    public Product create(AdminRequest.CreateProduct request) {
        var title = Title.create(request.title());
        var bio = Bio.create(request.productBio());
        var frontImage = FrontImage.create(request.frontImage().path(), request.frontImage().url());
        var backImage = BackImage.create(request.backImage().path(), request.backImage().url());
        var bannerImage = BannerImage.create(request.bannerImage().path(), request.bannerImage().url(), request.mobileImage().path(), request.mobileImage().url());

        return Product.builder()
            .title(title)
            .bio(bio)
            .frontImage(frontImage)
            .backImage(backImage)
            .bannerImage(bannerImage)
            .build();
    }

    @Override
    public void update(Product product, AdminRequest.UpdateProduct request) {
        var title = Title.create(request.title());
        var bio = Bio.create(request.productBio());
        var frontImage = FrontImage.create(request.frontImage().path(), request.frontImage().url());
        var backImage = BackImage.create(request.backImage().path(), request.backImage().url());
        var bannerImage = BannerImage.create(request.bannerImage().path(), request.bannerImage().url(), request.mobileImage().path(), request.mobileImage().url());

        product.update(title, bio, frontImage, backImage, bannerImage);
    }
}

package com.rudkids.core.admin.infrastructure;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.admin.service.MysteryProductFactory;
import com.rudkids.core.product.domain.*;
import org.springframework.stereotype.Component;

@Component
public class MysteryProductFactoryImpl implements MysteryProductFactory {

    @Override
    public MysteryProduct create(AdminRequest.CreateProduct request) {
        var title = Title.create(request.title());
        var bio = Bio.create(request.productBio());
        var frontImage = FrontImage.create(request.frontImage().path(), request.frontImage().url());
        var backImage = BackImage.create(request.backImage().path(), request.backImage().url());
        var bannerImage = BannerImage.create(request.bannerImage().path(), request.bannerImage().url(), request.mobileImage().path(), request.mobileImage().url());

        return MysteryProduct.builder()
            .title(title)
            .bio(bio)
            .frontImage(frontImage)
            .backImage(backImage)
            .bannerImage(bannerImage)
            .build();
    }

    @Override
    public void update(MysteryProduct product, AdminRequest.UpdateProduct request) {
        var title = Title.create(request.title());
        var bio = Bio.create(request.productBio());
        var frontImage = FrontImage.create(request.frontImage().path(), request.frontImage().url());
        var backImage = BackImage.create(request.backImage().path(), request.backImage().url());
        var bannerImage = BannerImage.create(request.bannerImage().path(), request.bannerImage().url(), request.mobileImage().path(), request.mobileImage().url());

        product.update(title, bio, frontImage, backImage, bannerImage);
    }
}

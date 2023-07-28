package com.rudkids.core.product.domain;

import com.rudkids.core.common.domain.AbstractEntity;
import com.rudkids.core.item.domain.Item;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(name = "tbl_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "product_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Embedded
    private Title title;

    @Embedded
    private ProductBio productBio;

    @Embedded
    private ProductFrontImage frontImage;

    @Embedded
    private ProductBackImage backImage;

    @Embedded
    private ProductMobileImage mobileImage;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus = ProductStatus.OPEN;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    private final List<Item> items = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ProductBannerImage> productBannerImages = new ArrayList<>();

    @Builder
    public Product(final Title title,
                   final ProductBio productBio,
                   final ProductFrontImage frontImage,
                   final ProductBackImage backImage,
                   final ProductMobileImage mobileImage
                   ) {
        this.title = title;
        this.productBio = productBio;
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.mobileImage = mobileImage;
    }

    public void update(final Title title,
                       final ProductBio productBio,
                       final ProductFrontImage frontImage,
                       final ProductBackImage backImage,
                       final ProductMobileImage mobileImage
                       ) {
        this.title = title;
        this.productBio = productBio;
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.mobileImage = mobileImage;
    }

    public void addBannerImage(ProductBannerImage productBannerImage) {
        productBannerImages.add(productBannerImage);
    }

    public void changeStatus(ProductStatus status) {
        this.productStatus = status;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public String getTitle() {
        return title.getValue();
    }

    public String getProductBio() {
        return productBio.getValue();
    }

    public String getFrontImagePath() {
        return frontImage.getPath();
    }

    public String getBackImagePath() {
        return backImage.getPath();
    }

    public List<String> getBannerPaths() {
        return productBannerImages.stream()
            .map(ProductBannerImage::getPath)
            .toList();
    }

    public String getFrontImageUrl() {
        return frontImage.getUrl();
    }

    public String getBackImageUrl() {
        return backImage.getUrl();
    }

    public String getMobileImagePath() {
        return mobileImage.getPath();
    }

    public String getMobileImageUrl() {
        return mobileImage.getUrl();
    }
}

package com.rudkids.core.product.domain;

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
@Table(name = "tbl_mystery_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MysteryProduct {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "mystery_product_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Embedded
    private Title title;

    @Embedded
    private Bio bio;

    @Embedded
    private FrontImage frontImage;

    @Embedded
    private BackImage backImage;

    @Embedded
    private BannerImage bannerImage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mysteryProduct", cascade = CascadeType.ALL)
    private final List<Item> items = new ArrayList<>();

    @Builder
    public MysteryProduct(final Title title,
                          final Bio bio,
                          final FrontImage frontImage,
                          final BackImage backImage,
                          final BannerImage bannerImage) {
        this.title = title;
        this.bio = bio;
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.bannerImage = bannerImage;
    }

    public void update(final Title title,
                       final Bio bio,
                       final FrontImage frontImage,
                       final BackImage backImage,
                       final BannerImage bannerImage
    ) {
        this.title = title;
        this.bio = bio;
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.bannerImage = bannerImage;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public String getTitle() {
        return title.getValue();
    }

    public String getBio() {
        return bio.getValue();
    }

    public String getFrontImagePath() {
        return frontImage.getPath();
    }

    public String getBackImagePath() {
        return backImage.getPath();
    }

    public String getBannerImagePath() {
        return bannerImage.getBannerPath();
    }

    public String getMobileBannerImagePath() {
        return bannerImage.getMobileBannerPath();
    }

    public String getFrontImageUrl() {
        return frontImage.getUrl();
    }

    public String getBackImageUrl() {
        return backImage.getUrl();
    }

    public String getBannerImageUrl() {
        return bannerImage.getBannerUrl();
    }

    public String getMobileBannerImageUrl() {
        return bannerImage.getMobileBannerUrl();
    }
}

package com.rudkids.core.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BannerImage {

    @Column(name = "banner_path")
    private String bannerPath;

    @Column(name = "banner_url")
    private String bannerUrl;

    @Column(name = "mobile_banner_path")
    private String mobileBannerPath;

    @Column(name = "mobile_banner_url")
    private String mobileBannerUrl;

    private BannerImage(String bannerPath, String bannerUrl, String mobileBannerPath, String mobileBannerUrl) {
        this.bannerPath = bannerPath;
        this.bannerUrl = bannerUrl;
        this.mobileBannerPath = mobileBannerPath;
        this.mobileBannerUrl = mobileBannerUrl;
    }

    public static BannerImage create(String bannerPath, String bannerUrl, String mobileBannerPath, String mobileBannerUrl) {
        return new BannerImage(bannerPath, bannerUrl, mobileBannerPath, mobileBannerUrl);
    }
}

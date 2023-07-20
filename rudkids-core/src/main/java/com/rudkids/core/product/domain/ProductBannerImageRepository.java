package com.rudkids.core.product.domain;

import java.util.List;

public interface ProductBannerImageRepository {
    void deletes(List<ProductBannerImage> productBannerImage);
    void delete(ProductBannerImage image);
}

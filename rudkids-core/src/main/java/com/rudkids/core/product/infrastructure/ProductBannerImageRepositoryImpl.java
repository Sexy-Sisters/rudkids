package com.rudkids.core.product.infrastructure;

import com.rudkids.core.product.domain.ProductBannerImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductBannerImageRepositoryImpl implements ProductBannerImageRepository {
    private final JpaProductBannerImageRepository productBannerImageRepository;

    @Override
    public List<String> getImageFileNames() {
        return productBannerImageRepository.findPathsByDeletedTrue();
    }
}

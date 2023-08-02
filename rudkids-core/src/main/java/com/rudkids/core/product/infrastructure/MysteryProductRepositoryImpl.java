package com.rudkids.core.product.infrastructure;

import com.rudkids.core.product.domain.MysteryProduct;
import com.rudkids.core.product.domain.MysteryProductRepository;
import com.rudkids.core.product.exception.MysteryProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MysteryProductRepositoryImpl implements MysteryProductRepository {
    private final JpaMysteryProductRepository mysteryProductRepository;

    @Override
    public void save(MysteryProduct mysteryProduct) {
        mysteryProductRepository.save(mysteryProduct);
    }

    @Override
    public MysteryProduct get(UUID mysteryProductId) {
        return mysteryProductRepository.findById(mysteryProductId)
            .orElseThrow(MysteryProductNotFoundException::new);
    }

    @Override
    public void delete(MysteryProduct mysteryProduct) {
        mysteryProductRepository.delete(mysteryProduct);
    }
}

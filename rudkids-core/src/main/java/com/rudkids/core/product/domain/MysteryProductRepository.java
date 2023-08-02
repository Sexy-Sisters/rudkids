package com.rudkids.core.product.domain;

import java.util.UUID;

public interface MysteryProductRepository {
    void save(MysteryProduct mysteryProduct);
    MysteryProduct get(UUID mysteryProductId);
    void delete(MysteryProduct mysteryProduct);
}

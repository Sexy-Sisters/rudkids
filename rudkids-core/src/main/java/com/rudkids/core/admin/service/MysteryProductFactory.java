package com.rudkids.core.admin.service;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.product.domain.MysteryProduct;

public interface MysteryProductFactory {
    MysteryProduct create(AdminRequest.CreateProduct request);
    void update(MysteryProduct product, AdminRequest.UpdateProduct request);
}

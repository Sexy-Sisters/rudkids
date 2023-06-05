package com.rudkids.rudkids.domain.delivery;

import com.rudkids.rudkids.domain.delivery.domain.Delivery;

public interface DeliveryStore {
    void store(Delivery delivery);
    void delete(Delivery delivery);
}

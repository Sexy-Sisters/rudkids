package com.rudkids.rudkids.interfaces.order.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public class OrderResponse {
    public record Main(UUID orderId, ZonedDateTime createdAt) {
    }
}

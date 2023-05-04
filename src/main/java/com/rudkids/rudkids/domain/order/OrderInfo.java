package com.rudkids.rudkids.domain.order;

import java.time.ZonedDateTime;
import java.util.UUID;

public class OrderInfo {
    public record Main(UUID orderId, ZonedDateTime createdAt) {
    }
}

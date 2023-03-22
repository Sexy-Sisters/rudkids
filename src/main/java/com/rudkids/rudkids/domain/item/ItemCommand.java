package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.LimitType;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public class ItemCommand {

    @Getter
    @Builder
    public static class RegisterRequest {
        private UUID productId;
        private String name;
        private int price;
        private int quantity;
        private LimitType limitType;
    }
}

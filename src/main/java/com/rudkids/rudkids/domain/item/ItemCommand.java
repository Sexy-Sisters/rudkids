package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.LimitType;
import lombok.Builder;
import lombok.Getter;

public class ItemCommand {

    @Getter
    @Builder
    public static class CreateRequest {
        private String name;
        private int price;
        private int quantity;
        private LimitType limitType;
    }
}

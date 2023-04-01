package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import lombok.Builder;
import lombok.Getter;

public class ItemInfo {

    @Getter
    @Builder
    public static class Main {
        private String name;
        private int price;
        private ItemStatus itemStatus;
    }

    @Getter
    @Builder
    public static class Detail {
        private String name;
        private String bio;
        private int price;
        private int quantity;
        private LimitType limitType;
        private ItemStatus itemStatus;
    }
}

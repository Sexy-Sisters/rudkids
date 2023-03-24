package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.ItemStatus;
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
}

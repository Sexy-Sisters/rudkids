package com.rudkids.core.item.domain;

import com.rudkids.core.item.exception.ItemStatusNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ItemStatus {
    PREPARING("판매준비중"),
    SELLING("판매중"),
    SOLD_OUT("판매종료");

    private final String description;

    public static ItemStatus toEnum(String description) {
        return Arrays.stream(values())
            .filter(it -> it.isSameDescription(description))
            .findFirst()
            .orElseThrow(ItemStatusNotFoundException::new);
    }

    private boolean isSameDescription(String description) {
        return this.name().equals(description);
    }
}

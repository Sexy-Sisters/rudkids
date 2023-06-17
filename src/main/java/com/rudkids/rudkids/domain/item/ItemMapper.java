package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMapper {

    public ItemInfo.Detail toDetail(Item item, List<ItemInfo.ItemOptionGroupInfo> itemOptionSeriesList) {
        return ItemInfo.Detail.builder()
            .enName(item.getEnName())
            .koName(item.getKoName())
            .itemBio(item.getItemBio())
            .price(item.getPrice())
            .quantity(item.getQuantity())
            .limitType(item.getLimitType())
            .imageUrls(item.getImageUrls())
            .itemStatus(item.getItemStatus())
            .itemOptionGroupInfoList(itemOptionSeriesList)
            .build();
    }
}

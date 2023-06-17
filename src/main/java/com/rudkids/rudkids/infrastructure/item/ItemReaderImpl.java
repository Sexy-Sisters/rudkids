package com.rudkids.rudkids.infrastructure.item;

import com.rudkids.rudkids.domain.item.ItemInfo;
import com.rudkids.rudkids.domain.item.ItemReader;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ItemReaderImpl implements ItemReader {
    private final ItemRepository itemRepository;

    @Override
    public Item getItem(UUID id) {
        return itemRepository.findById(id)
            .orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public List<ItemInfo.ItemOptionGroupInfo> getItemOptionSeries(Item item) {
        var itemOptionGroupList = item.getItemOptionGroups();
        return itemOptionGroupList.stream()
            .map(itemOptionGroup -> {
                var itemOptionList = itemOptionGroup.getItemOptions();
                var itemOptionInfoList = itemOptionList.stream()
                    .map(ItemInfo.ItemOptionInfo::new)
                    .toList();

                return new ItemInfo.ItemOptionGroupInfo(itemOptionGroup, itemOptionInfoList);
            }).toList();
    }
}

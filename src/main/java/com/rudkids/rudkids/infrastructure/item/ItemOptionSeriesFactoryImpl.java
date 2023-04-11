package com.rudkids.rudkids.infrastructure.item;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemOptionSeriesFactory;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroup;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroupStore;
import com.rudkids.rudkids.domain.item.domain.itemOption.ItemOptionStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemOptionSeriesFactoryImpl implements ItemOptionSeriesFactory {
    private final ItemOptionGroupStore itemOptionGroupStore;
    private final ItemOptionStore itemOptionStore;

    @Override
    public List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest command, Item item) {
        var itemOptionGroupRequestList = command.itemOptionGroupList();

        return itemOptionGroupRequestList.stream()
            .map(requestItemOptionGroup -> {
                var initItemOptionGroup = requestItemOptionGroup.toEntity(item);
                var itemOptionGroup = itemOptionGroupStore.store(initItemOptionGroup);

                requestItemOptionGroup.itemOptionList().forEach(requestItemOption -> {
                    var initItemOption = requestItemOption.toEntity(itemOptionGroup);
                    itemOptionStore.store(initItemOption);
                });

                return itemOptionGroup;
            })
            .collect(Collectors.toList());
    }
}

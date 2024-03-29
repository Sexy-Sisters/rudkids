package com.rudkids.core.item.infrastructure;

import com.rudkids.core.item.domain.option.ItemOption;
import com.rudkids.core.item.domain.option.ItemOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemOptionRepositoryImpl implements ItemOptionRepository {
    private final JpaItemOptionRepository itemOptionRepository;

    @Override
    public void deletes(List<ItemOption> options) {
        itemOptionRepository.deleteAllInBatch(options);
    }
}

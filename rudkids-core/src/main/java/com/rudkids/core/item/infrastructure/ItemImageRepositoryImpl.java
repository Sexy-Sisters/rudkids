package com.rudkids.core.item.infrastructure;

import com.rudkids.core.item.domain.ItemImage;
import com.rudkids.core.item.domain.ItemImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemImageRepositoryImpl implements ItemImageRepository {
    private final JpaItemImageRepository itemImageRepository;

    @Override
    public void deletes(List<ItemImage> images) {
        itemImageRepository.deleteAllInBatch(images);
    }
}

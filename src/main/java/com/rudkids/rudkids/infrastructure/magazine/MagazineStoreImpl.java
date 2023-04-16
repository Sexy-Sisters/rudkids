package com.rudkids.rudkids.infrastructure.magazine;

import com.rudkids.rudkids.domain.magazine.MagazineStore;
import com.rudkids.rudkids.domain.magazine.domain.Magazine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MagazineStoreImpl implements MagazineStore {
    private final MagazineRepository magazineRepository;

    @Override
    public void store(Magazine magazine) {
        magazineRepository.save(magazine);
    }
}

package com.rudkids.rudkids.infrastructure.magazine;

import com.rudkids.rudkids.domain.magazine.MagazineReader;
import com.rudkids.rudkids.domain.magazine.domain.Magazine;
import com.rudkids.rudkids.domain.magazine.exception.MagazineNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MagazineReaderImpl implements MagazineReader {
    private final MagazineRepository magazineRepository;

    @Override
    public Magazine getMagazine(UUID magazineId) {
        return magazineRepository.findById(magazineId)
                .orElseThrow(MagazineNotFoundException::new);
    }

    @Override
    public List<Magazine> getMagazines() {
        return magazineRepository.findAll();
    }
}

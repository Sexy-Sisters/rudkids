package com.rudkids.rudkids.domain.magazine.service;

import com.rudkids.rudkids.domain.magazine.*;
import com.rudkids.rudkids.domain.magazine.domain.Magazine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MagazineServiceImpl implements MagazineService {
    private final MagazineStore magazineStore;
    private final MagazineReader magazineReader;
    private final MagazineMapper magazineMapper;
    private final MagazineFactory magazineFactory;

    @Override
    public void create(MagazineCommand.Create command) {
        var initMagazine = magazineFactory.create(command);
        magazineStore.store(initMagazine);
    }

    @Override
    public List<MagazineInfo.Main> findAll() {
        return magazineReader.getMagazines().stream()
                .map(magazineMapper::toMain)
                .toList();
    }

    @Override
    public MagazineInfo.Detail find(UUID magazineId) {
        var magazine = magazineReader.getMagazine(magazineId);
        return magazineMapper.toDetail(magazine);
    }

    @Override
    public void update(UUID magazineId, MagazineCommand.Update command) {
        Magazine magazine = magazineReader.getMagazine(magazineId);
        magazineFactory.update(command, magazine);
    }

    @Override
    public void delete(UUID magazineId) {
        Magazine magazine = magazineReader.getMagazine(magazineId);
        magazineStore.delete(magazine);
    }
}

package com.rudkids.rudkids.domain.magazine.service;

import com.rudkids.rudkids.domain.magazine.MagazineCommand;
import com.rudkids.rudkids.domain.magazine.MagazineInfo;

import java.util.List;
import java.util.UUID;

public interface MagazineService {
    void create(UUID userId, MagazineCommand.Create command);
    List<MagazineInfo.Main> findAll();
    MagazineInfo.Detail find(UUID magazineId);
    void update(UUID magazineId, MagazineCommand.Update command);
    void delete(UUID magazineId);
}

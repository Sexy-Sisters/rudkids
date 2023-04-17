package com.rudkids.rudkids.domain.magazine.service;

import com.rudkids.rudkids.domain.magazine.MagazineCommand;

import java.util.UUID;

public interface MagazineService {
    void create(UUID userId, MagazineCommand.Create command);
    void update(UUID userId, UUID magazineId, MagazineCommand.Update command);
    void delete(UUID userId, UUID magazineId);
}

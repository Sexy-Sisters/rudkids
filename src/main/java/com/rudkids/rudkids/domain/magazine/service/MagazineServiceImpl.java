package com.rudkids.rudkids.domain.magazine.service;

import com.rudkids.rudkids.domain.magazine.*;
import com.rudkids.rudkids.domain.magazine.domain.Content;
import com.rudkids.rudkids.domain.magazine.domain.Magazine;
import com.rudkids.rudkids.domain.magazine.domain.Title;
import com.rudkids.rudkids.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MagazineServiceImpl implements MagazineService {
    private final UserReader userReader;
    private final MagazineStore magazineStore;
    private final MagazineReader magazineReader;
    private final MagazineMapper magazineMapper;

    @Override
    public void create(UUID userId, MagazineCommand.Create command) {
        var user = userReader.getUser(userId);
        user.validateAdminRole();
        Title title = Title.create(command.title());
        Content content = Content.create(command.content());
        Magazine magazine = Magazine.create(user, title, content);
        magazineStore.store(magazine);
    }

    @Override
    public List<MagazineInfo.All> findAll() {
        return magazineReader.getMagazines().stream()
                .map(magazineMapper::toInfo)
                .toList();
    }

    @Override
    public void update(UUID userId, UUID magazineId, MagazineCommand.Update command) {
        var user = userReader.getUser(userId);
        user.validateAdminRole();
        Magazine magazine = magazineReader.getMagazine(magazineId);
        Title title = Title.create(command.title());
        Content content = Content.create(command.content());
        magazine.update(title, content);
    }

    @Override
    public void delete(UUID userId, UUID magazineId) {
        var user = userReader.getUser(userId);
        user.validateAdminRole();
        Magazine magazine = magazineReader.getMagazine(magazineId);
        magazineStore.delete(magazine);
    }
}

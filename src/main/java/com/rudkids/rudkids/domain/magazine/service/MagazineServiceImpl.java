package com.rudkids.rudkids.domain.magazine.service;

import com.rudkids.rudkids.domain.magazine.MagazineCommand;
import com.rudkids.rudkids.domain.magazine.MagazineStore;
import com.rudkids.rudkids.domain.magazine.domain.Content;
import com.rudkids.rudkids.domain.magazine.domain.Magazine;
import com.rudkids.rudkids.domain.magazine.domain.Title;
import com.rudkids.rudkids.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MagazineServiceImpl implements MagazineService {
    private final UserReader userReader;
    private final MagazineStore magazineStore;

    @Override
    public void create(UUID userId, MagazineCommand.Create command) {
        var user = userReader.getUser(userId);
        user.validateAdminRole();

        Title title = Title.create(command.title());
        Content content = Content.create(command.content());
        Magazine magazine = Magazine.create(user, title, content);
        magazineStore.store(magazine);
    }
}

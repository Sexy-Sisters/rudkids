package com.rudkids.rudkids.domain.community.service;

import com.rudkids.rudkids.domain.community.*;
import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityServiceImpl implements CommunityService {
    private final UserReader userReader;
    private final CommunityStore communityStore;
    private final CommunityReader communityReader;
    private final CommunityMapper communityMapper;

    @Override
    public void create(UUID userId, CommunityCommand.Create command) {
        var user = userReader.getUser(userId);
        var community = command.toEntity(user);
        community.choiceType(command.type());
        communityStore.store(community);
    }

    @Override
    public List<CommunityInfo.Main> findAll(String type) {
        return communityReader.getCommunities(type).stream()
                .map(communityMapper::toMain)
                .toList();
    }

    @Override
    public CommunityInfo.Detail find(UUID magazineId) {
        var magazine = communityReader.getMagazine(magazineId);
        return communityMapper.toDetail(magazine);
    }

    @Override
    public void update(UUID magazineId, CommunityCommand.Update command) {
        Community community = communityReader.getMagazine(magazineId);
//        communityFactory.update(command, community);
    }

    @Override
    public void delete(UUID magazineId) {
        Community community = communityReader.getMagazine(magazineId);
        communityStore.delete(community);
    }
}

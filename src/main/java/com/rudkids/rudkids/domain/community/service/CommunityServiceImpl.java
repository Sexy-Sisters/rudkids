package com.rudkids.rudkids.domain.community.service;

import com.rudkids.rudkids.domain.community.*;
import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.community.domain.Content;
import com.rudkids.rudkids.domain.community.domain.Title;
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
    public UUID create(UUID userId, CommunityCommand.Create command) {
        var user = userReader.getUser(userId);
        var community = command.toEntity(user);
        community.choiceType(command.type());
        communityStore.store(community);
        return community.getId();
    }

    @Override
    public List<CommunityInfo.Main> findAll(String type) {
        return communityReader.getCommunities(type).stream()
                .map(communityMapper::toMain)
                .toList();
    }

    @Override
    public CommunityInfo.Detail find(UUID communityId) {
        var magazine = communityReader.get(communityId);
        return communityMapper.toDetail(magazine);
    }

    @Override
    public void update(UUID userId, UUID communityId, CommunityCommand.Update command) {
        var user = userReader.getUser(userId);
        var community = communityReader.get(communityId);
        community.validateHasSameUser(user);

        var title = Title.create(command.title());
        var content = Content.create(command.content());
        community.update(title, content);
    }

    @Override
    public void delete(UUID userId, UUID communityId) {
        var user = userReader.getUser(userId);
        Community community = communityReader.get(communityId);
        community.validateHasSameUser(user);
        communityStore.delete(community);
    }
}

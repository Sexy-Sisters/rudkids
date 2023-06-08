package com.rudkids.rudkids.domain.community.service;

import com.rudkids.rudkids.domain.community.*;
import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.image.service.ImageService;
import com.rudkids.rudkids.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    private final ImageService imageService;

    @Override
    public UUID create(UUID userId, CommunityCommand.Create command) {
        var user = userReader.getUser(userId);
        var community = command.toEntity(user);
        community.choiceType(command.type());
        communityStore.store(community);
        return community.getId();
    }

    @Override
    public List<CommunityInfo.Main> findAll(String type, Pageable pageable) {
        return communityReader.getCommunities(type, pageable).stream()
                .map(communityMapper::toMain)
                .toList();
    }

    @Override
    public CommunityInfo.Detail find(UUID communityId) {
        var community = communityReader.get(communityId);
        community.increaseViewCount();
        return communityMapper.toDetail(community);
    }

    @Override
    public void update(UUID userId, UUID communityId, CommunityCommand.Update command) {
        var user = userReader.getUser(userId);
        var community = communityReader.get(communityId);
        community.validateHasSameUser(user);
        imageService.delete(community);
        community.update(command.toTitle(), command.toContent(), command.toImage());
    }

    @Override
    public void delete(UUID userId, UUID communityId) {
        var user = userReader.getUser(userId);
        Community community = communityReader.get(communityId);
        community.validateHasSameUser(user);
        imageService.delete(community);
        communityStore.delete(community);
    }
}

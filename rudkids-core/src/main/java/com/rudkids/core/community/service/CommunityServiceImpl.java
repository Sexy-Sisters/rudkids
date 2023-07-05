package com.rudkids.core.community.service;

import com.rudkids.core.community.domain.*;
import com.rudkids.core.community.dto.CommunityRequest;
import com.rudkids.core.community.dto.CommunityResponse;
import com.rudkids.core.image.service.ImageDeletedEvent;
import com.rudkids.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public UUID create(UUID userId, CommunityRequest.Create request) {
        var user = userRepository.getUser(userId);
        var community = request.toEntity(user);
        community.choiceType(request.type());
        communityRepository.save(community);
        return community.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunityResponse.Main> getAll(String type, Pageable pageable) {
        return communityRepository.getCommunities(type, pageable).stream()
                .map(CommunityResponse.Main::new)
                .toList();
    }

    @Override
    public CommunityResponse.Detail get(UUID communityId) {
        var community = communityRepository.getCommunity(communityId);
        community.increaseViewCount();
        return new CommunityResponse.Detail(community);
    }

    @Override
    public void update(UUID userId, UUID communityId, CommunityRequest.Update request) {
        var user = userRepository.getUser(userId);
        var community = communityRepository.getCommunity(communityId);
        community.validateHasSameUser(user);
        deleteImage(community);

        var title = Title.create(request.title());
        var content = Content.create(request.content());
        var image = CommunityImage.create(request.image().path(), request.image().path());
        community.update(title, content, image);
    }

    @Override
    public void delete(UUID userId, UUID communityId) {
        var user = userRepository.getUser(userId);
        var community = communityRepository.getCommunity(communityId);
        community.validateHasSameUser(user);
        deleteImage(community);
        communityRepository.delete(community);
    }

    private void deleteImage(Community community) {
        eventPublisher.publishEvent(new ImageDeletedEvent(community.getPath()));
    }
}

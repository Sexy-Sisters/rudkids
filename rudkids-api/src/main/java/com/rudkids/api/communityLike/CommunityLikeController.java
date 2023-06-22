package com.rudkids.api.communityLike;

import com.rudkids.api.auth.AuthenticationPrincipal;
import com.rudkids.core.auth.infrastructure.dto.AuthUser;
import com.rudkids.core.communityLike.service.CommunityLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/community-like")
public class CommunityLikeController {
    private final CommunityLikeService communityLikeService;

    @PutMapping("/{id}")
    public void like(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable("id") UUID communityId
    ) {
        communityLikeService.like(loginUser.id(), communityId);
    }
}

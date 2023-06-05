package com.rudkids.rudkids.interfaces.communityLike;

import com.rudkids.rudkids.domain.communityLike.service.CommunityLikeService;
import com.rudkids.rudkids.interfaces.auth.AuthenticationPrincipal;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/community-like")
@RequiredArgsConstructor
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

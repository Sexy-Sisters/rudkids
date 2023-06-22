package com.rudkids.api.community;

import com.rudkids.api.auth.AuthenticationPrincipal;
import com.rudkids.core.auth.infrastructure.dto.AuthUser;
import com.rudkids.core.community.dto.CommunityRequest;
import com.rudkids.core.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/community")
public class CommunityController {
    private final CommunityService communityService;

    @PostMapping
    public ResponseEntity create(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestBody CommunityRequest.Create request
    ) {
        var response = communityService.create(loginUser.id(), request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity findAll(
        @RequestParam("type") String type,
        @PageableDefault Pageable pageable
    ) {
        var response = communityService.getAll(type, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable UUID id) {
        var response = communityService.get(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public void update(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable("id") UUID communityId,
        @RequestBody CommunityRequest.Update request
    ) {
        communityService.update(loginUser.id(), communityId, request);
    }

    @DeleteMapping("/{id}")
    public void delete(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable("id") UUID communityId
    ) {
        communityService.delete(loginUser.id(), communityId);
    }
}

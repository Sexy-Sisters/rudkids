package com.rudkids.rudkids.interfaces.community;

import com.rudkids.rudkids.domain.community.service.CommunityService;
import com.rudkids.rudkids.interfaces.auth.AuthenticationPrincipal;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import com.rudkids.rudkids.interfaces.community.dto.CommunityDtoMapper;
import com.rudkids.rudkids.interfaces.community.dto.CommunityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/community")
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;
    private final CommunityDtoMapper communityDtoMapper;

    @PostMapping
    public ResponseEntity create(@AuthenticationPrincipal AuthUser.Login loginUser,
                                 @RequestBody CommunityRequest.Create request) {
        var command = communityDtoMapper.toCommand(request);
        var response = communityService.create(loginUser.id(), command);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam("type") String type) {
        var response = communityService.findAll(type);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable UUID id) {
        var response = communityService.find(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public void update(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable("id") UUID communityId,
        @RequestBody CommunityRequest.Update request
    ) {
        var command = communityDtoMapper.toCommand(request);
        communityService.update(loginUser.id(), communityId, command);
    }

    @DeleteMapping("/{id}")
    public void delete(@AuthenticationPrincipal AuthUser.Login loginUser,
                       @PathVariable("id") UUID communityId) {
        communityService.delete(loginUser.id(), communityId);
    }
}

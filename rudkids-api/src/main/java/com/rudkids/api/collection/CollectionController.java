package com.rudkids.api.collection;

import com.rudkids.api.auth.AuthenticationPrincipal;
import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.collection.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/collection")
public class CollectionController {
    private final CollectionService collectionService;

    @GetMapping
    public ResponseEntity getAll(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestParam("category") String category) {
        var response = collectionService.getAll(loginUser.id(), category);
        return ResponseEntity.ok(response);
    }
}

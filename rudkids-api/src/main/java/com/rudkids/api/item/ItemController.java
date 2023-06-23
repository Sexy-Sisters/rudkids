package com.rudkids.api.item;

import com.rudkids.api.auth.AuthenticationPrincipal;
import com.rudkids.core.auth.infrastructure.dto.AuthUser;
import com.rudkids.core.item.dto.ItemRequest;
import com.rudkids.core.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/item")
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/{product-id}")
    public ResponseEntity<Void> create(
        @PathVariable(name = "product-id") UUID productId,
        @RequestBody ItemRequest.Create request,
        @AuthenticationPrincipal AuthUser.Login loginUser
    ) {
        itemService.create(loginUser.id(), productId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity get(@RequestParam String name) {
        var response = itemService.get(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/popular")
    public ResponseEntity getPopularItems(@PageableDefault Pageable pageable) {
        var response = itemService.getPopularItems(pageable);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable(name = "id") UUID itemId,
        @RequestBody ItemRequest.Update  request
    ) {
        itemService.update(loginUser.id(), itemId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity changeStatus(
        @PathVariable(name = "id") UUID itemId,
        @RequestBody ItemRequest.ChangeStatus request,
        @AuthenticationPrincipal AuthUser.Login loginUser
    ) {
        var response = itemService.changeStatus(loginUser.id(), itemId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable(name = "id") UUID itemId,
        @AuthenticationPrincipal AuthUser.Login loginUser
    ) {
        itemService.delete(loginUser.id(), itemId);
        return ResponseEntity.ok().build();
    }
}

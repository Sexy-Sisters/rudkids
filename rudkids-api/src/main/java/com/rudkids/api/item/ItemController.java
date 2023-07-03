package com.rudkids.api.item;

import com.rudkids.api.auth.AuthenticationPrincipal;
import com.rudkids.core.auth.dto.AuthUser;
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

    @GetMapping("/video-image")
    public ResponseEntity getItemVideoImages(@PageableDefault Pageable pageable) {
        var response = itemService.getItemVideoImages(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/video")
    public ResponseEntity getItemVideo(@RequestParam String name) {
        var response = itemService.getItemVideo(name);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<Void> update(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable(name = "name") String itemName,
        @RequestBody ItemRequest.Update  request
    ) {
        itemService.update(loginUser.id(), itemName, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{name}")
    public ResponseEntity changeStatus(
        @PathVariable(name = "name") String itemId,
        @RequestBody ItemRequest.ChangeStatus request,
        @AuthenticationPrincipal AuthUser.Login loginUser
    ) {
        var response = itemService.changeStatus(loginUser.id(), itemId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> delete(
        @PathVariable(name = "name") String itemName,
        @AuthenticationPrincipal AuthUser.Login loginUser
    ) {
        itemService.delete(loginUser.id(), itemName);
        return ResponseEntity.ok().build();
    }
}

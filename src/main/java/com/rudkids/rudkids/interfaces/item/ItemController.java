package com.rudkids.rudkids.interfaces.item;

import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.item.service.ItemService;
import com.rudkids.rudkids.interfaces.auth.AuthenticationPrincipal;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import com.rudkids.rudkids.interfaces.item.dto.ItemDtoMapper;
import com.rudkids.rudkids.interfaces.item.dto.ItemRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ItemDtoMapper itemDtoMapper;

    @PostMapping("/{product-id}")
    public void create(
        @PathVariable(name = "product-id") UUID productId,
        @RequestBody ItemRequest.RegisterItem request,
        @AuthenticationPrincipal AuthUser.Login loginUser
    ) {
        var command = itemDtoMapper.to(request);
        itemService.create(command, productId, loginUser.id());
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity find(@PathVariable UUID id) {
        var info = itemService.find(id);
        var response = itemDtoMapper.to(info);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public void update(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable(name = "id") UUID itemId,
        @RequestBody ItemRequest.Update  request
    ) {
        var command = itemDtoMapper.toCommand(request);
        itemService.update(command, itemId, loginUser.id());
    }

    @PutMapping("/{id}")
    public ItemStatus changeStatus(
        @PathVariable(name = "id") UUID userId,
        @RequestParam ItemStatus status,
        @AuthenticationPrincipal AuthUser.Login loginUser
    ) {
        return itemService.changeItemStatus(userId, status, loginUser.id());
    }

    @DeleteMapping("/{id}")
    public void delete(
        @PathVariable(name = "id") UUID itemId,
        @AuthenticationPrincipal AuthUser.Login loginUser
    ) {
        itemService.delete(itemId, loginUser.id());
    }
}

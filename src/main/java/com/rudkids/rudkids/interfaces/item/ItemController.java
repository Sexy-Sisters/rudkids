package com.rudkids.rudkids.interfaces.item;

import com.rudkids.rudkids.domain.item.service.ItemService;
import com.rudkids.rudkids.interfaces.auth.AuthenticationPrincipal;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import com.rudkids.rudkids.interfaces.item.dto.ItemDtoMapper;
import com.rudkids.rudkids.interfaces.item.dto.ItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestBody ItemRequest.RegisterItem request
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

    @PutMapping("/{id}/on-sales")
    public ResponseEntity changeOnSales(@PathVariable UUID id) {
        var status = itemService.changeOnSales(id);
        return ResponseEntity.ok(status);
    }

    @DeleteMapping("/{id}/end-of-sales")
    public ResponseEntity changeEndOfSales(@PathVariable UUID id) {
        var status = itemService.changeEndOfSales(id);
        return ResponseEntity.ok(status);
    }

    @PutMapping("/{id}/prepare")
    public ResponseEntity changePrepare(@PathVariable UUID id) {
        var status = itemService.changePrepare(id);
        return ResponseEntity.ok(status);
    }
}

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
    public String changeOnSales(@PathVariable UUID id) {
        return itemService.changeOnSales(id);
    }

    @DeleteMapping("/{id}/end-of-sales")
    public String changeEndOfSales(@PathVariable UUID id) {
        return itemService.changeEndOfSales(id);
    }

    @PutMapping("/{id}/prepare")
    public String changePrepare(@PathVariable UUID id) {
        return itemService.changePrepare(id);
    }
}

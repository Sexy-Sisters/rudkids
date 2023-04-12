package com.rudkids.rudkids.interfaces.item;

import com.rudkids.rudkids.domain.item.service.ItemService;
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
    public void registerItem(
        @PathVariable(name = "product-id") UUID productId,
        @RequestBody ItemRequest.RegisterItem request
    ) {
        var command = itemDtoMapper.to(request);
        itemService.registerItem(command, productId);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity findItem(@PathVariable UUID id) {
        var info = itemService.findItemDetail(id);
        var response = itemDtoMapper.to(info);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/on-sales")
    public void changeOnSales(@PathVariable UUID id) {
        itemService.changeOnSales(id);
    }

    @DeleteMapping("/{id}/end-of-sales")
    public void changeEndOfSales(@PathVariable UUID id) {
        itemService.changeEndOfSales(id);
    }
}

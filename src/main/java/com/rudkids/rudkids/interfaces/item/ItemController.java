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

    @PostMapping
    public void registerItem(@RequestBody ItemRequest.Register request) {
        var command = itemDtoMapper.to(request);
        itemService.registerItem(command);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity findItems(@PathVariable(name = "product-id") UUID productId) {
        var infoList = itemService.findItems(productId);
        var response = infoList.stream()
            .map(itemDtoMapper::to)
            .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity findItem(@PathVariable UUID id) {
        var info = itemService.findItemDetail(id);
        var response = itemDtoMapper.to(info);
        return ResponseEntity.ok(response);
    }
}

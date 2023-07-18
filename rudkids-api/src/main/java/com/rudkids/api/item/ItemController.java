package com.rudkids.api.item;

import com.rudkids.core.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/item")
public class ItemController {
    private final ItemService itemService;

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
}

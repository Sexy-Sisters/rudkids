package com.rudkids.rudkids.interfaces.magazine;

import com.rudkids.rudkids.domain.magazine.service.MagazineService;
import com.rudkids.rudkids.interfaces.magazine.dto.MagazineDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/magazine")
@RequiredArgsConstructor
public class MagazineController {
    private final MagazineService magazineService;

    @GetMapping
    public ResponseEntity findAll() {
        var response = magazineService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable UUID id) {
        var response = magazineService.find(id);
        return ResponseEntity.ok(response);
    }
}

package com.rudkids.rudkids.interfaces.magazine;

import com.rudkids.rudkids.domain.magazine.service.MagazineService;
import com.rudkids.rudkids.interfaces.auth.AuthenticationPrincipal;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import com.rudkids.rudkids.interfaces.magazine.dto.MagazineDtoMapper;
import com.rudkids.rudkids.interfaces.magazine.dto.MagazineRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/magazine")
@RequiredArgsConstructor
public class MagazineController {
    private final MagazineService magazineService;
    private final MagazineDtoMapper magazineDtoMapper;

    @PostMapping
    public void create(
            @AuthenticationPrincipal AuthUser.Login loginUser,
            @RequestBody MagazineRequest.Create request
    ) {
        var command = magazineDtoMapper.to(request);
        magazineService.create(loginUser.id(), command);
    }

    @PutMapping("/{id}")
    public void update(
            @AuthenticationPrincipal AuthUser.Login loginUser,
            @PathVariable("id") UUID magazineId,
            @RequestBody MagazineRequest.Update request
    ) {
        var command = magazineDtoMapper.to(request);
        magazineService.update(loginUser.id(), magazineId, command);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @AuthenticationPrincipal AuthUser.Login loginUser,
            @PathVariable("id") UUID magazineId
    ) {
        magazineService.delete(loginUser.id(), magazineId);
    }
}

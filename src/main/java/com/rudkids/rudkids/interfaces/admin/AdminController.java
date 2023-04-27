package com.rudkids.rudkids.interfaces.admin;

import com.rudkids.rudkids.domain.admin.service.AdminService;
import com.rudkids.rudkids.domain.magazine.service.MagazineService;
import com.rudkids.rudkids.domain.product.service.ProductService;
import com.rudkids.rudkids.interfaces.admin.dto.AdminDtoMapper;
import com.rudkids.rudkids.interfaces.admin.dto.AdminRequest;
import com.rudkids.rudkids.interfaces.auth.AuthenticationPrincipal;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import com.rudkids.rudkids.interfaces.magazine.dto.MagazineDtoMapper;
import com.rudkids.rudkids.interfaces.magazine.dto.MagazineRequest;
import com.rudkids.rudkids.interfaces.product.dto.ProductDtoMapper;
import com.rudkids.rudkids.interfaces.product.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final AdminDtoMapper adminDtoMapper;
    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;
    private final MagazineService magazineService;
    private final MagazineDtoMapper magazineDtoMapper;

    @GetMapping("/user")
    public ResponseEntity searchUser(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestParam String email
    ) {
        var response = adminService.searchUser(loginUser.id(), email);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/user/{id}")
    public void changeUserRole(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable("id") UUID userId,
        @RequestBody AdminRequest.ChangeUserRole request
        ) {
        var command = adminDtoMapper.toCommand(request);
        adminService.changeUserRole(loginUser.id(), userId, command);
    }

    @PostMapping("/product")
    public void create(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        ProductRequest.Create request
    ) {
        var command = productDtoMapper.toCommand(request);
        productService.create(command, loginUser.id());
    }

    @PutMapping("/product/{id}")
    public void changeProductStatus(
        @PathVariable(name = "id") UUID productId,
        @RequestBody ProductRequest.ChangeStatus request,
        @AuthenticationPrincipal AuthUser.Login loginUser
    ) {
        productService.changeStatus(request.productStatus(), productId, loginUser.id());
    }

    @PutMapping("/product/{id}")
    public void updateProduct(
        @PathVariable(name = "id") UUID productId,
        @RequestBody ProductRequest.Update request,
        @AuthenticationPrincipal AuthUser.Login loginUser
    ) {
        var command = productDtoMapper.toCommand(request);
        productService.update(command, productId, loginUser.id());
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(
        @PathVariable(name = "id") UUID productId,
        @AuthenticationPrincipal AuthUser.Login loginUser
    ) {
        productService.delete(productId, loginUser.id());
    }

    @PostMapping("/magazine")
    public void createMagazine(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestBody MagazineRequest.Create request
    ) {
        var command = magazineDtoMapper.to(request);
        magazineService.create(loginUser.id(), command);
    }

    @PutMapping("/magazine/{id}")
    public void updateMagazine(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable UUID id,
        @RequestBody MagazineRequest.Update request
    ) {
        var command = magazineDtoMapper.to(request);
        magazineService.update(loginUser.id(), id, command);
    }

    @DeleteMapping("/magazine/{id}")
    public void deleteMagazine(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable UUID id
    ) {
        magazineService.delete(loginUser.id(), id);
    }
}

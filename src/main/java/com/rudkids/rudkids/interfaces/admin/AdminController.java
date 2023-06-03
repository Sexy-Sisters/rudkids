package com.rudkids.rudkids.interfaces.admin;

import com.rudkids.rudkids.domain.admin.service.AdminService;
import com.rudkids.rudkids.domain.community.service.CommunityService;
import com.rudkids.rudkids.domain.order.service.OrderService;
import com.rudkids.rudkids.domain.product.service.ProductService;
import com.rudkids.rudkids.interfaces.admin.dto.AdminDtoMapper;
import com.rudkids.rudkids.interfaces.admin.dto.AdminRequest;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import com.rudkids.rudkids.interfaces.community.dto.CommunityDtoMapper;
import com.rudkids.rudkids.interfaces.community.dto.CommunityRequest;
import com.rudkids.rudkids.interfaces.order.dto.OrderRequest;
import com.rudkids.rudkids.interfaces.product.dto.ProductDtoMapper;
import com.rudkids.rudkids.interfaces.product.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private final CommunityService communityService;
    private final OrderService orderService;
    private final ProductDtoMapper productDtoMapper;
    private final CommunityDtoMapper communityDtoMapper;

    @GetMapping("/user")
    public ResponseEntity searchUser(
        @AuthenticationAdminAuthority
        @RequestParam String email
    ) {
        var response = adminService.searchUser(email);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/user/{id}")
    public void changeUserRole(
        @AuthenticationAdminAuthority
        @PathVariable("id") UUID userId,
        @RequestBody AdminRequest.ChangeUserRole request
    ) {
        var command = adminDtoMapper.toCommand(request);
        adminService.changeUserRole(userId, command);
    }

    @PostMapping("/product")
    public void create(
        @AuthenticationAdminAuthority
        @RequestBody ProductRequest.Create request
    ) {
        var command = productDtoMapper.toCommand(request);
        productService.create(command);
    }

    @PatchMapping("/product/{id}")
    public void changeProductStatus(
        @AuthenticationAdminAuthority
        @PathVariable(name = "id") UUID productId,
        @RequestBody ProductRequest.ChangeStatus request
    ) {
        productService.changeStatus(request.productStatus(), productId);
    }

    @PutMapping("/product/{id}")
    public void updateProduct(
        @AuthenticationAdminAuthority
        @PathVariable(name = "id") UUID productId,
        @RequestBody ProductRequest.Update request
    ) {
        var command = productDtoMapper.toCommand(request);
        productService.update(command, productId);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(
        @AuthenticationAdminAuthority
        @PathVariable(name = "id") UUID productId
    ) {
        productService.delete(productId);
    }

    @PutMapping("/magazine/{id}")
    public void updateMagazine(
        @AuthenticationAdminAuthority
        @PathVariable("id") UUID magazineId,
        @RequestBody CommunityRequest.Update request
    ) {
        var command = communityDtoMapper.toCommand(request);
        communityService.update(magazineId, command);
    }

    @DeleteMapping("/magazine/{id}")
    public void deleteMagazine(
        @AuthenticationAdminAuthority
        @PathVariable("id") UUID magazineId
    ) {
        communityService.delete(magazineId);
    }

    @GetMapping("/order")
    public ResponseEntity findAll(
        @AuthenticationAdminAuthority AuthUser.Login loginUser,
        @PageableDefault Pageable pageable
    ) {
        var info = orderService.findAll(pageable);
        return ResponseEntity.ok(info);
    }

    @PatchMapping("/order/{id}")
    public void changeStatus(
        @AuthenticationAdminAuthority
        @PathVariable(name = "id") UUID orderId,
        @RequestBody OrderRequest.ChangeStatus request
    ) {
        orderService.changeStatus(request.orderStatus(), orderId);
    }
}

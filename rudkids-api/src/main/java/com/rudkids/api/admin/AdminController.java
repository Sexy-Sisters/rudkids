package com.rudkids.api.admin;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.admin.service.AdminService;
import com.rudkids.core.auth.infrastructure.dto.AuthUser;
import com.rudkids.core.item.service.ItemService;
import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.service.OrderService;
import com.rudkids.core.product.dto.ProductRequest;
import com.rudkids.core.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;
    private final ProductService productService;
    private final OrderService orderService;
    private final ItemService itemService;

    @GetMapping("/user")
    public ResponseEntity searchUser(
        @AuthenticationAdminAuthority AuthUser.Login loginUser,
        @RequestParam String email
    ) {
        var response = adminService.searchUser(email);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<Void> changeUserRole(
        @AuthenticationAdminAuthority AuthUser.Login loginUser,
        @PathVariable("id") UUID userId,
        @RequestBody AdminRequest.ChangeUserRole request
    ) {
        adminService.changeUserRole(userId, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/product")
    public ResponseEntity<Void> createProduct(
        @AuthenticationAdminAuthority AuthUser.Login loginUser,
        @RequestBody ProductRequest.Create request
    ) {
        productService.create(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<Void> changeProductStatus(
        @AuthenticationAdminAuthority AuthUser.Login loginUser,
        @PathVariable(name = "id") UUID productId,
        @RequestBody ProductRequest.ChangeStatus request
    ) {
        productService.changeStatus(productId, request.productStatus());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Void> updateProduct(
        @AuthenticationAdminAuthority AuthUser.Login loginUser,
        @PathVariable(name = "id") UUID productId,
        @RequestBody ProductRequest.Update request
    ) {
        productService.update(productId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(
        @AuthenticationAdminAuthority AuthUser.Login loginUser,
        @PathVariable(name = "id") UUID productId
    ) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/item")
    public ResponseEntity searchItemId(
        @AuthenticationAdminAuthority AuthUser.Login loginUser,
        @RequestParam String name
    ) {
        var response = itemService.search(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/order")
    public ResponseEntity findAllOrder(
        @AuthenticationAdminAuthority AuthUser.Login loginUser,
        @PageableDefault Pageable pageable
    ) {
        var info = orderService.getAll(pageable);
        return ResponseEntity.ok(info);
    }

    @PatchMapping("/order/{id}")
    public ResponseEntity<Void> changeOrderStatus(
        @AuthenticationAdminAuthority AuthUser.Login loginUser,
        @PathVariable(name = "id") UUID orderId,
        @RequestBody OrderRequest.ChangeStatus request
    ) {
        orderService.changeStatus(orderId, request);
        return ResponseEntity.ok().build();
    }
}

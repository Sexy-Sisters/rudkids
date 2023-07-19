package com.rudkids.api.admin;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@AuthenticationAdminAuthority
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    /*
    이메일로 유저들의 정보를 검색한다
     */
    @GetMapping("/user")
    public ResponseEntity searchUsers(@RequestParam String email) {
        var response = adminService.searchUsers(email);
        return ResponseEntity.ok(response);
    }

    /*
    유저의 권한을 변경한다
     */
    @PatchMapping("/user/{id}")
    public ResponseEntity<Void> changeUserRole(
        @PathVariable("id") UUID userId,
        @RequestBody AdminRequest.ChangeUserRole request
    ) {
        adminService.changeUserRole(userId, request);
        return ResponseEntity.ok().build();
    }

    /*
    프로덕트를 생성한다
     */
    @PostMapping("/product")
    public ResponseEntity<Void> create(@RequestBody AdminRequest.CreateProduct request) {
        adminService.createProduct(request);
        return ResponseEntity.ok().build();
    }

    /*
    프로덕트의 상태를 변경한다
     */
    @PatchMapping("/product/{id}")
    public ResponseEntity<Void> changeStatus(
        @PathVariable(name = "id") UUID productId,
        @RequestBody AdminRequest.ChangeProductStatus request
    ) {
        adminService.changeProductStatus(productId, request);
        return ResponseEntity.ok().build();
    }

    /*
    프로덕트의 정보를 수정한다
     */
    @PutMapping("/product/{id}")
    public ResponseEntity<Void> updateProduct(
        @PathVariable(name = "id") UUID productId,
        @RequestBody AdminRequest.UpdateProduct request
    ) {
        adminService.updateProduct(productId, request);
        return ResponseEntity.ok().build();
    }

    /*
    프로덕트를 삭제한다
     */
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") UUID productId) {
        adminService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

    /*
    아이템을 생성한다
     */
    @PostMapping("/item/{id}")
    public ResponseEntity<Void> createItem(
        @PathVariable(name = "id") UUID productId,
        @RequestBody AdminRequest.CreateItem request
    ) {
        adminService.createItem(productId, request);
        return ResponseEntity.ok().build();
    }

    /*
    아이템 정보를 수정한다
     */
    @PutMapping("/item/update/{name}")
    public ResponseEntity<Void> updateItem(
        @PathVariable(name = "name") String name,
        @RequestBody AdminRequest.UpdateItem request
    ) {
        adminService.updateItem(name, request);
        return ResponseEntity.ok().build();
    }

    /*
    아이템 상태를 변경한다
     */
    @PutMapping("/item/{name}")
    public ResponseEntity<Void> changeItemStatus(
        @PathVariable(name = "name") String itemId,
        @RequestBody AdminRequest.ChangeItemStatus request
    ) {
        adminService.changeItemStatus(itemId, request);
        return ResponseEntity.ok().build();
    }

    /*
    아이템을 삭제한다
     */
    @DeleteMapping("/item/{name}")
    public ResponseEntity<Void> deleteItem(@PathVariable(name = "name") String name) {
        adminService.deleteItem(name);
        return ResponseEntity.ok().build();
    }

    /*
    비디오를 생성한다
     */
    @PostMapping("/video")
    public ResponseEntity<Void> createVideo(@RequestBody AdminRequest.CreateVideo request) {
        adminService.createVideo(request);
        return ResponseEntity.ok().build();
    }

    /*
    비디오를 수정한다
     */
    @PutMapping("/video/{id}")
    public ResponseEntity<Void> updateVideo(@PathVariable("id") UUID videoId, AdminRequest.UpdateVideo request) {
        adminService.updateVideo(videoId, request);
        return ResponseEntity.ok().build();
    }

    /*
    비디오를 삭제한다
     */
    @DeleteMapping("/video/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable("id") UUID videoId) {
        adminService.deleteVideo(videoId);
        return ResponseEntity.ok().build();
    }

    /*
    모든 주문을 조회한다
     */
    @GetMapping("/order")
    public ResponseEntity getAllOrder(
        @RequestParam("deliveryStatus") String deliveryStatus,
        @RequestParam("orderStatus") String orderStatus,
        @RequestParam("deliveryTrackingNumber") String deliveryTrackingNumber,
        @RequestParam("customerName") String customerName,
        @PageableDefault Pageable pageable
    ) {
        var response = adminService.getAllOrder(deliveryStatus, orderStatus, deliveryTrackingNumber, customerName, pageable);
        return ResponseEntity.ok(response);
    }

    /*
    택배 송장을 입력한다
     */
    @PostMapping("/order/{id}")
    public ResponseEntity<Void> registerDeliveryTrackingNumber(
        @PathVariable("id") UUID orderId,
        @RequestBody AdminRequest.DeliveryTrackingNumber request
    ) {
        adminService.registerDeliveryTrackingNumber(orderId, request);
        return ResponseEntity.ok().build();
    }

    /*
    환불을 실행한다
     */
}

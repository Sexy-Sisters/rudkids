package com.rudkids.core.admin.service;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.admin.dto.AdminResponse;
import com.rudkids.core.order.dto.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AdminService {
    List<AdminResponse.UserInfo> searchUser(String email);
    void changeUserRole(UUID userId, AdminRequest.ChangeUserRole request);
    Page<OrderResponse.Main> getAllOrders(Pageable pageable);
}

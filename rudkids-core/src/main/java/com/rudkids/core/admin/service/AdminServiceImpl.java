package com.rudkids.core.admin.service;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.admin.dto.AdminResponse;
import com.rudkids.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<AdminResponse.UserInfo> searchUser(String email) {
        return userRepository.getUsers(email).stream()
            .map(AdminResponse.UserInfo::new)
            .toList();
    }

    @Override
    public void changeUserRole(UUID userId, AdminRequest.ChangeUserRole request) {
        var user = userRepository.getUser(userId);
        user.changeRole(request.roleType());
    }
}

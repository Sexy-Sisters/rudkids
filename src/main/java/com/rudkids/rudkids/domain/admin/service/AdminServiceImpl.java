package com.rudkids.rudkids.domain.admin.service;

import com.rudkids.rudkids.domain.admin.AdminCommand;
import com.rudkids.rudkids.domain.admin.AdminInfo;
import com.rudkids.rudkids.domain.admin.AdminMapper;
import com.rudkids.rudkids.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {
    private final UserReader userReader;
    private final AdminMapper adminMapper;

    @Transactional(readOnly = true)
    @Override
    public List<AdminInfo.User> searchUser(String email) {
        return userReader.getUsers(email).stream()
            .map(adminMapper::toInfo)
            .toList();
    }

    @Override
    public void changeUserRole(UUID userId, AdminCommand.ChangeUserRole command) {
        var user = userReader.getUser(userId);
        user.changeRole(command.role());
    }
}

package com.rudkids.rudkids.domain.admin.service;

import com.rudkids.rudkids.domain.admin.AdminCommand;
import com.rudkids.rudkids.domain.admin.AdminInfo;
import com.rudkids.rudkids.domain.admin.AdminMapper;
import com.rudkids.rudkids.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {
    private final UserReader userReader;
    private final AdminMapper adminMapper;

    @Transactional(readOnly = true)
    @Override
    public AdminInfo.User searchUser(UUID userId, String email) {
        var user = userReader.getUser(userId);
        user.validateAdminRole();
        var searchedUser = userReader.getUser(email);
        return adminMapper.toInfo(searchedUser);
    }

    @Override
    public void changeUserRole(UUID userId, UUID targetUserId, AdminCommand.ChangeUserRole command) {
        var user = userReader.getUser(userId);
        user.validateAdminRole();
        var targetUser = userReader.getUser(targetUserId);
        targetUser.changeRole(command.role());
    }
}

package com.rudkids.core.user.infrastructure;

import com.rudkids.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    List<User> findByEmailContaining(String email);

    @Query("SELECT u.profileImage.path FROM User u WHERE u.profileImage.deleted is true")
    List<String> findPathsByDeletedTrue();
}

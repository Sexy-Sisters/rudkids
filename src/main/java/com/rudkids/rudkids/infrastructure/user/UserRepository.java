package com.rudkids.rudkids.infrastructure.user;

import com.rudkids.rudkids.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    List<User> findByEmailContaining(String email);
    boolean existsById(UUID id);
}

package com.rudkids.rudkids.domain.user.repository;

import com.rudkids.rudkids.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    boolean existsById(UUID id);
}

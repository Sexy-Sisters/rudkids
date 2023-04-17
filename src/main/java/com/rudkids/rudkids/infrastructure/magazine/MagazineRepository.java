package com.rudkids.rudkids.infrastructure.magazine;

import com.rudkids.rudkids.domain.magazine.domain.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MagazineRepository extends JpaRepository<Magazine, UUID> {
    Optional<Magazine> findByTitleValue(String title);
}

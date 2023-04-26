package com.rudkids.rudkids.infrastructure.image;

import com.rudkids.rudkids.domain.image.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}

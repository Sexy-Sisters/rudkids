package com.rudkids.rudkids.util;

import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract public class BaseEntityId implements Persistable<UUID> {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @Transient
    private boolean isNew = true;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PostLoad
    @PostPersist
    public void load() {
        isNew = false;
    }
}

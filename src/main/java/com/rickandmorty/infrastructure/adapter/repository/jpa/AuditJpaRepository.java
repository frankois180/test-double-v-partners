package com.rickandmorty.infrastructure.adapter.repository.jpa;

import com.rickandmorty.infrastructure.adapter.repository.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditJpaRepository extends JpaRepository<AuditEntity, Long> {
}

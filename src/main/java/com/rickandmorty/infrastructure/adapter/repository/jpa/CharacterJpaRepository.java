package com.rickandmorty.infrastructure.adapter.repository.jpa;

import com.rickandmorty.infrastructure.adapter.repository.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterJpaRepository extends JpaRepository<CharacterEntity, String> {
}

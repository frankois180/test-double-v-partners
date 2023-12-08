package com.rickandmorty.infrastructure.adapter;

import com.rickandmorty.domain.model.Character;
import com.rickandmorty.domain.port.CharacterRepositoryPort;
import com.rickandmorty.infrastructure.adapter.repository.jpa.CharacterJpaRepository;
import com.rickandmorty.infrastructure.adapter.repository.mapper.CharacterMapper;
import com.rickandmorty.infrastructure.adapter.shared.PageAsk;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class CharacterRepositoryAdapter implements CharacterRepositoryPort {
    private final CharacterJpaRepository characterJpaRepository;

    @Override
    public Character save(Character character) {
        return CharacterMapper.fromEntity(characterJpaRepository.save(CharacterMapper.fromDomain(character)));
    }

    @Override
    public Supplier<Stream<Character>> findAll(PageAsk pageAsk) {
        return characterJpaRepository.findAll(PageRequest.of(pageAsk.getPage(),
                pageAsk.getSize())).map(CharacterMapper::fromEntity);
    }

    @Override
    public Character findByName(String name) {
        return characterJpaRepository.findById(name).map(CharacterMapper::fromEntity).orElse(null);
    }
}

package com.rickandmorty.domain.service;

import com.rickandmorty.domain.exception.CharacterNotificationCode;
import com.rickandmorty.domain.exception.AlreadyApiException;
import com.rickandmorty.domain.model.Character;
import com.rickandmorty.domain.port.CharacterRepositoryPort;
import com.rickandmorty.infrastructure.adapter.repository.mapper.CharacterMapper;
import com.rickandmorty.infrastructure.adapter.shared.PageAsk;
import com.rickandmorty.infrastructure.restclient.CharactersClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepositoryPort characterRepositoryPort;
    private final CharactersClient charactersClient;

    public Character save(Character character) {
        if(characterRepositoryPort.findByName(character.getName()) != null || charactersClient.findAllCharacter().getResults().stream().anyMatch(item -> item.getName().equals(character.getName()))){
            throw new AlreadyApiException(CharacterNotificationCode.DATA_ALREADY_API);
        }

        return characterRepositoryPort.save(character);
    }

    public Supplier<Stream<Character>> findAll(PageAsk pageAsk) {
        return characterRepositoryPort.findAll(pageAsk);
    }

    public List<Character> findAllApi() {
        return charactersClient.findAllCharacter().getResults().stream().map(CharacterMapper::fromApiResponse).collect(Collectors.toList());
    }
}

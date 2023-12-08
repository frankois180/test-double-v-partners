package com.rickandmorty.domain.service;

import com.rickandmorty.domain.exception.AlreadyApiException;
import com.rickandmorty.domain.model.Character;
import com.rickandmorty.domain.model.type.StatusType;
import com.rickandmorty.domain.port.CharacterRepositoryPort;
import com.rickandmorty.infrastructure.restclient.CharactersClient;
import com.rickandmorty.infrastructure.restclient.model.CharacterResponse;
import com.rickandmorty.infrastructure.restclient.model.DataCharacterResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;


class CharacterServiceTest {


    private  CharacterRepositoryPort characterRepositoryPort;


    private CharactersClient charactersClient;

    @InjectMocks
    private CharacterService characterService;

    @BeforeEach
    public void init() {
        characterRepositoryPort = Mockito.mock(CharacterRepositoryPort.class);
        charactersClient = Mockito.mock(CharactersClient.class);

        characterService = new CharacterService(characterRepositoryPort,charactersClient);

    }

    @Test
    void when_saveCharacter_with_requestOK_then_return_object_notNull(){
        Mockito.when(characterRepositoryPort.findByName(Mockito.any())).thenReturn(null);
        Mockito.when(charactersClient.findAllCharacter()).thenReturn(buildDataCharacterResponse());
        Mockito.when( characterRepositoryPort.save(Mockito.any())).thenReturn(buildCharacter());

        Character character = buildCharacter();
        Character  result =  characterService.save(character);
        Assertions.assertThat(result).isNotNull();

    }

    @Test
    void when_saveCharacter_with_name_characterExist_then_return_AlreadyApiException() {
        Mockito.when(characterRepositoryPort.findByName(Mockito.any())).thenReturn(buildCharacter());
        Mockito.when(charactersClient.findAllCharacter()).thenReturn(buildDataCharacterResponse());

        Character character = buildCharacter();
        assertThrows(AlreadyApiException.class, () ->
                characterService.save(character));

    }


    private Character buildCharacter (){
      return   Character.builder()
                .gender("Male")
                .name("Rick Sanchez")
                .status(StatusType.ALIVE)
                .image("https://rickandmortyapi.com/api/character?page=2")
                .build();
    }

    private DataCharacterResponse buildDataCharacterResponse(){
        return DataCharacterResponse.builder()
                .results(Arrays.asList(buildCharacterResponse()))
                .build();
    }

    private CharacterResponse buildCharacterResponse(){
     return    CharacterResponse.builder()
                .gender("Male")
                .name("Rick Sanchez x")
                .status(StatusType.ALIVE.getCode())
                .image("https://rickandmortyapi.com/api/character?page=2")
                .build();
    }

}
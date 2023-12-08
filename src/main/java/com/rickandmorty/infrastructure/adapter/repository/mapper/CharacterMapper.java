package com.rickandmorty.infrastructure.adapter.repository.mapper;

import com.rickandmorty.domain.model.Character;
import com.rickandmorty.domain.model.type.StatusType;
import com.rickandmorty.infrastructure.adapter.repository.entity.CharacterEntity;
import com.rickandmorty.infrastructure.restclient.model.CharacterResponse;
public class CharacterMapper {

    private CharacterMapper(){

    }
    public static Character fromEntity(CharacterEntity characterEntity) {
        return Character.builder()
                .gender(characterEntity.getGender())
                .image(characterEntity.getImage())
                .name(characterEntity.getName())
                .status(StatusType.findByPrimaryKey(characterEntity.getStatus())).
                        build();
    }

    public static CharacterEntity fromDomain(Character character) {
        return CharacterEntity.builder()
                .gender(character.getGender())
                .image(character.getImage())
                .name(character.getName())
                .status(character.getStatus().getCode()).
                        build();
    }

    public static Character fromApiResponse(CharacterResponse characterResponse) {
        return Character.builder()
                .status(StatusType.findByPrimaryKey(characterResponse.getStatus()))
                .name(characterResponse.getName())
                .image(characterResponse.getImage())
                .gender(characterResponse.getGender())
                .build();
    }
}

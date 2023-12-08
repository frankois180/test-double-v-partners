package com.rickandmorty.application.mapper;

import com.rickandmorty.domain.model.Character;
import com.rickandmorty.domain.model.type.StatusType;
import com.rickandmorty.infrastructure.controller.dto.CharacterDto;

public class CharacterAppMapper {

    private CharacterAppMapper(){
    }
    public static CharacterDto fromDto (Character character){
        return CharacterDto.builder()
                .gender(character.getGender())
                .image(character.getImage())
                .name(character.getName())
                .status(character.getStatus().getCode()).
                        build();
    }
   public static Character fromDomain(CharacterDto characterDto){
        return     Character.builder()
               .gender(characterDto.getGender())
               .image(characterDto.getImage())
               .name(characterDto.getName())
               .status(StatusType.findByPrimaryKey(characterDto.getStatus())).
                       build();
   }
}

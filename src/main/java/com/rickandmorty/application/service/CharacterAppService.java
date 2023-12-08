package com.rickandmorty.application.service;

import com.rickandmorty.application.mapper.CharacterAppMapper;
import com.rickandmorty.domain.model.Character;
import com.rickandmorty.domain.service.CharacterService;
import com.rickandmorty.infrastructure.adapter.shared.PageAsk;
import com.rickandmorty.infrastructure.controller.dto.CharacterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class CharacterAppService {

    private final CharacterService characterService;

    public CharacterDto save(CharacterDto characterDto) {
        return CharacterAppMapper.fromDto(characterService.save(CharacterAppMapper.fromDomain(characterDto)));
    }

    public Supplier<Stream<CharacterDto>> findAll(PageAsk pageAsk) {
        return ((Page<Character>) characterService.findAll(pageAsk)).map(CharacterAppMapper::fromDto);
    }

    public List<CharacterDto> findAllApi(String name, String status, String gender, Boolean orderName, Boolean orderStatus, Boolean oderGender) {

        List<CharacterDto> list = characterService.findAllApi().stream()
                .filter(character -> filter(character, name, status, gender))
                .map(CharacterAppMapper::fromDto).collect(Collectors.toList());

        orderBy(list, orderName, orderStatus, oderGender);

        return list;
    }

    private boolean filter(Character character, String name, String status, String gender) {
        boolean result = false;

        if (name != null && status == null && gender == null) {
            result = character.getName().toLowerCase().contains(name.toLowerCase());
        }
        if (name != null && status != null && gender == null) {
            result = character.getName().toLowerCase().contains(name.toLowerCase()) && character.getStatus().getCode().toLowerCase().contains(status.toLowerCase());
        }

        if (name != null && status == null && gender != null) {
            result = character.getName().toLowerCase().contains(name.toLowerCase()) &&
                    character.getGender().toLowerCase().contains(gender.toLowerCase());
        }

        if (name != null && status != null && gender != null) {
            result = character.getName().toLowerCase().contains(name.toLowerCase()) &&
                    character.getGender().toLowerCase().contains(gender.toLowerCase()) && character.getStatus().getCode().toLowerCase().contains(status.toLowerCase());
        }

        if (status != null && name == null && gender == null) {
            result = character.getStatus().getCode().toLowerCase().contains(status.toLowerCase());
        }

        if (gender != null && name == null && status == null) {
            result = character.getGender().toLowerCase().contains(gender.toLowerCase());
        }

        if (gender != null && status != null && name == null) {
            result = character.getGender().toLowerCase().contains(gender.toLowerCase()) && character.getStatus().getCode().toLowerCase().contains(status.toLowerCase());
        }
        if (name == null && gender == null && status == null) {
            result = true;
        }

        return result;
    }


    private void orderBy(List<CharacterDto> list, Boolean name, Boolean status, Boolean gender) {

        if (name != null && status == null && gender == null) {
            Collections.sort(list, Comparator.comparing(CharacterDto::getName));
        }
        if (name != null && status != null && gender == null) {
            Collections.sort(list, Comparator.comparing(CharacterDto::getName));
            Collections.sort(list, Comparator.comparing(CharacterDto::getStatus));
        }
        if (name != null && status == null && gender != null) {
            Collections.sort(list, Comparator.comparing(CharacterDto::getName));
            Collections.sort(list, Comparator.comparing(CharacterDto::getGender));
        }

        if (status != null && name == null && gender == null) {
            Collections.sort(list, Comparator.comparing(CharacterDto::getStatus));
        }

        if (gender != null && name == null && status == null) {
            Collections.sort(list, Comparator.comparing(CharacterDto::getGender));
        }

        if (gender != null && status != null && name == null) {
            Collections.sort(list, Comparator.comparing(CharacterDto::getStatus));
            Collections.sort(list, Comparator.comparing(CharacterDto::getGender));
        }

        if (gender != null && status != null && name != null) {
            Collections.sort(list, Comparator.comparing(CharacterDto::getName));
            Collections.sort(list, Comparator.comparing(CharacterDto::getStatus));
            Collections.sort(list, Comparator.comparing(CharacterDto::getGender));
        }
    }

}

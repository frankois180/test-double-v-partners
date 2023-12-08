package com.rickandmorty.infrastructure.restclient;

import com.rickandmorty.infrastructure.restclient.model.DataCharacterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "characters", url = "${feign.client.characters.url}")
public interface CharactersClient {

    @GetMapping(value = "/character")
    DataCharacterResponse findAllCharacter();

}

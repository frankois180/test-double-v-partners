package com.rickandmorty.domain.port;

import com.rickandmorty.domain.model.Character;
import com.rickandmorty.infrastructure.adapter.shared.PageAsk;

import java.util.function.Supplier;
import java.util.stream.Stream;

public interface CharacterRepositoryPort {

    /**
     * Method to create a Character
     *
     * @param character: Character object
     * @return Character: Character object
     */
    Character save(Character character);

    /**
     *  Method to search all products
     *
     * @param pageAsk: paging parameters
     * @return List<Character>: Character list
     */

    Supplier<Stream<Character>> findAll(PageAsk pageAsk);

    Character findByName(String name);

}

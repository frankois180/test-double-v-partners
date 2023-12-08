package com.rickandmorty.infrastructure.restclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterResponse {

    private String name;
    private String gender;
    private String status;
    private String image;

}

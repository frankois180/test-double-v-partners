package com.rickandmorty.infrastructure.adapter.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "character")
public class CharacterEntity {

    @Id
    private String name;
    private String gender;
    private String status;
    private String image;
}

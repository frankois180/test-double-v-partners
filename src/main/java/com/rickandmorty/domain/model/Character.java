package com.rickandmorty.domain.model;

import com.rickandmorty.domain.model.type.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
@AllArgsConstructor
public class Character {
     @With
     String name;
     String gender;
     StatusType status;
     String image;
}

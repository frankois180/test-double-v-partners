package com.rickandmorty.infrastructure.controller;

import com.rickandmorty.application.service.CharacterAppService;
import com.rickandmorty.infrastructure.controller.dto.ApiResponseDto;
import com.rickandmorty.infrastructure.controller.dto.CharacterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterAppService characterAppService;

    @PostMapping
    public ApiResponseDto save(@Validated @RequestBody CharacterDto characterDto) {
        return ApiResponseDto.builder()
                .data(characterAppService.save(characterDto))
                .build();

    }

    @GetMapping
    public ApiResponseDto findAll(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String status,
                                  @RequestParam(required = false) String gender,
                                  @RequestParam(required = false) Boolean orderName,
                                  @RequestParam(required = false) Boolean orderStatus,
                                  @RequestParam(required = false) Boolean oderGender) {
        return ApiResponseDto.builder()
                .data(characterAppService.findAllApi(name, status, gender, orderName, orderStatus, oderGender))
                .build();
    }

}

package com.rickandmorty.infrastructure.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.rickandmorty.infrastructure.restclient")
public class FeignClientConfig {
}
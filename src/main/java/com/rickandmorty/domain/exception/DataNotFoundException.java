package com.rickandmorty.domain.exception;


import com.rickandmorty.infrastructure.config.CharacterMessage;

public class DataNotFoundException extends RickandmortyException {

    public DataNotFoundException(CharacterNotificationCode notificationCode, Object... param) {
        super(CharacterMessage.msg(notificationCode.getMessage(), param), notificationCode);
    }
}
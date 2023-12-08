package com.rickandmorty.domain.exception;

import com.rickandmorty.infrastructure.config.CharacterMessage;

public class AlreadyApiException extends RickandmortyException {

    public AlreadyApiException(CharacterNotificationCode notificationCode, Object... param) {
        super(CharacterMessage.msg(notificationCode.getMessage(), param), notificationCode);
    }
}
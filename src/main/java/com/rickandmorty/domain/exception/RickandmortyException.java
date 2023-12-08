package com.rickandmorty.domain.exception;

import lombok.Getter;

public class RickandmortyException extends RuntimeException {

    @Getter
    private final CharacterNotificationCode notificationCode;

    public RickandmortyException(String message, CharacterNotificationCode notificationCode) {

        super(message);
        this.notificationCode = notificationCode;

    }

}
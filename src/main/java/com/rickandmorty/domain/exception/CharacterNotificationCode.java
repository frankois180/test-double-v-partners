package com.rickandmorty.domain.exception;

import com.rickandmorty.infrastructure.config.CharacterMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum CharacterNotificationCode {

    DATA_ALREADY_API(CharacterMessage.MsgName.DATA_ALREADY_API, "IN_AR_API"),
    TYPE_NOT_FOUND(CharacterMessage.MsgName.TYPE_NOT_FOUND, "IN_ST_NF");

    private CharacterMessage.MsgName message;
    private String apiCode;
}

package com.rickandmorty.domain.model.type;

import com.fasterxml.jackson.annotation.JsonValue;
import com.rickandmorty.domain.exception.CharacterNotificationCode;
import com.rickandmorty.domain.exception.DataNotFoundException;
import lombok.Getter;

import java.util.HashMap;

@Getter
public enum StatusType {


    DEAD("Dead"), UNKNOWN("unknown"), ALIVE("Alive");
    private static final HashMap<String, StatusType> ENUM_MAP_BY_CODE = new HashMap<>();
    private final String code;

    StatusType(String code) {
        this.code = code;
    }

    static {
        ENUM_MAP_BY_CODE.put(DEAD.getCode(),DEAD);
        ENUM_MAP_BY_CODE.put(UNKNOWN.getCode(), UNKNOWN);
        ENUM_MAP_BY_CODE.put(ALIVE.getCode(), ALIVE);

    }

    public static StatusType findByPrimaryKey(String id) {
        if (id != null && ENUM_MAP_BY_CODE.containsKey(id)) {
            return ENUM_MAP_BY_CODE.get(id);
        } else {
            return null;
        }
    }


}

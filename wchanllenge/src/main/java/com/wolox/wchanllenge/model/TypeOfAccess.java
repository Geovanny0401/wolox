package com.wolox.wchanllenge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum TypeOfAccess {
     READ(1), WRITE(2),READING_WRITING(3), NONE(0);

    @Setter
    @Getter
    private long id;

    public static TypeOfAccess listarPorAccessId(long id ){
        for (TypeOfAccess typeOfAccess : values()) {
            if (typeOfAccess.id ==id) {
                return typeOfAccess;
            }
        }
        return NONE;
    }
}

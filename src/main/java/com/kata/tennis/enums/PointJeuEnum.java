package com.kata.tennis.enums;

public enum PointJeuEnum {
    ZERO(0),
    UN(15),
    DEUX(30),
    TROIS(40),
    QUATRE(50);

    private final Integer id;

    PointJeuEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}

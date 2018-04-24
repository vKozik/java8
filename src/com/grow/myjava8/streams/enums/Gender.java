package com.grow.myjava8.streams.enums;

public enum Gender {
    MALE,
    FEMALE;

    public static Gender getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}

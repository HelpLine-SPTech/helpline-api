package com.helpline.helplineapi.enums;

public enum UserTypeEnum {
    COMMON,

    ONG;

    public static UserTypeEnum fromInteger(int x) {
        var values = UserTypeEnum.values();
        return values[x];
    }
}

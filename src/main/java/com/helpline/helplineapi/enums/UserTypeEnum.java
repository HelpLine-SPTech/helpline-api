package com.helpline.helplineapi.enums;


/**
 * User login type defined by helpline
 */
public enum UserTypeEnum {

    /**
     * Is a donator or a volunteer.
     */
    COMMON,

    /**
     * A ONG.
     */
    ONG;

    /**
     * Get the enum value base on the given integer
     * @param x int thar represent the enum value
     * @return the enum value based on x
     */
    public static UserTypeEnum fromInteger(int x) {
        var values = UserTypeEnum.values();
        return values[x];
    }
}

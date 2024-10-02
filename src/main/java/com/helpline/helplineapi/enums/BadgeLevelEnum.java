package com.helpline.helplineapi.enums;

import lombok.Getter;

/**
 * Enum containing all possible badge levels
 */
@Getter
public enum BadgeLevelEnum {
    ZERO_STAR(0, 0),
    ONE_STAR(10, 100000),
    TWO_STAR(20, 200000),
    THREE_STAR(30, 300000),
    FOUR_STAR(40, 400000),
    FIVE_STAR(50, 500000);
    
    BadgeLevelEnum(int itemThreshold, int monetaryThreshold) {
        this.itemThreshold = itemThreshold;
        this.monetaryThreshold = monetaryThreshold;
    }
    
    private final int itemThreshold;
    
    private final int monetaryThreshold;
}

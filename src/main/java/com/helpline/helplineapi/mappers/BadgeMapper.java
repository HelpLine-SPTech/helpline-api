package com.helpline.helplineapi.mappers;

import com.helpline.helplineapi.enums.BadgeLevelEnum;
import com.helpline.helplineapi.enums.BadgeTypeEnum;

public abstract class BadgeMapper {

    public static BadgeLevelEnum getNextLevel(int amount, BadgeTypeEnum badgeType) {
        if(badgeType == BadgeTypeEnum.MONETARY) return getMonetaryNextLevel(amount);
        else return getItemNextLevel(amount);
    }

    private static BadgeLevelEnum getMonetaryNextLevel(int amount) {
        if (amount >= BadgeLevelEnum.FIVE_STAR.getMonetaryThreshold()) return BadgeLevelEnum.FIVE_STAR;
        if (amount >= BadgeLevelEnum.FOUR_STAR.getMonetaryThreshold()) return BadgeLevelEnum.FOUR_STAR;
        if (amount >= BadgeLevelEnum.THREE_STAR.getMonetaryThreshold()) return BadgeLevelEnum.THREE_STAR;
        if (amount >= BadgeLevelEnum.TWO_STAR.getMonetaryThreshold()) return BadgeLevelEnum.TWO_STAR;
        if (amount >= BadgeLevelEnum.ONE_STAR.getMonetaryThreshold()) return BadgeLevelEnum.ONE_STAR;
        else return BadgeLevelEnum.ZERO_STAR;
    }

    private static BadgeLevelEnum getItemNextLevel(int amount) {
        if (amount >= BadgeLevelEnum.FIVE_STAR.getItemThreshold()) return BadgeLevelEnum.FIVE_STAR;
        if (amount >= BadgeLevelEnum.FOUR_STAR.getItemThreshold()) return BadgeLevelEnum.FOUR_STAR;
        if (amount >= BadgeLevelEnum.THREE_STAR.getItemThreshold()) return BadgeLevelEnum.THREE_STAR;
        if (amount >= BadgeLevelEnum.TWO_STAR.getItemThreshold()) return BadgeLevelEnum.TWO_STAR;
        if (amount >= BadgeLevelEnum.ONE_STAR.getItemThreshold()) return BadgeLevelEnum.ONE_STAR;
        else return BadgeLevelEnum.ZERO_STAR;
    }
}

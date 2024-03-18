package com.helpline.helplineapi.enums;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public enum ErrorCodeEnum {

    USER_ALREADY_EXISTS_ERROR(1),

    NOT_FOUND_ERROR(2),

    UNEXPECTED_ERROR(3);

    public final int number;


    public String message;

    ErrorCodeEnum(int number) {
        this.number = number;
    }
}

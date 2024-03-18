package com.helpline.helplineapi.data.contract;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {

    private ArrayList<ErrorCodeEnum> errors = new ArrayList<>();

    private ArrayList<StackTraceElement> stackTrace;

    public void addError(ErrorCodeEnum error) { errors.add(error); }

    public boolean getSuccess() {
        return errors.isEmpty();
    }

    public void setStackTrace(StackTraceElement[] stackTrace) {
        this.stackTrace = new ArrayList<>(List.of(stackTrace));
    }
}

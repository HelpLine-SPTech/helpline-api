package com.helpline.helplineapi.data.contract.auth;

import com.helpline.helplineapi.enums.ErrorCodeEnum;

import java.util.ArrayList;

public abstract class BaseResponse {

    public ArrayList<ErrorCodeEnum> errors;

}

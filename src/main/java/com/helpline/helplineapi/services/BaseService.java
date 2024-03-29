package com.helpline.helplineapi.services;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class BaseService<TRequest, TResponse extends BaseResponse> {

    public ResponseEntity<TResponse> process(TRequest request) {
        var response = new BaseResponse();
        try {
            var validationResponse = this.validateService(request);

            if(!validationResponse.getSuccess()) {
                return ResponseEntity.badRequest().body(validationResponse);
            }

            var finalResponse = this.processService(request);

            if(!finalResponse.getErrors().isEmpty()) {
                return ResponseEntity.badRequest().body(finalResponse);
            }

            return ResponseEntity.ok(finalResponse);
        } catch (Exception ex) {
            response.addError(ErrorCodeEnum.UNEXPECTED_ERROR);
            response.setStackTrace(ex.getStackTrace());

            return ResponseEntity.status(500).body((TResponse) response);
        }
    }

    protected abstract TResponse processService(TRequest request);

    protected abstract TResponse validateService(TRequest request);


}

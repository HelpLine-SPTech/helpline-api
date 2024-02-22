package com.helpline.helplineapi.services;

import org.springframework.http.ResponseEntity;

public abstract class BaseService<TRequest, TResponse> {

    public ResponseEntity<TResponse> process(TRequest request) {
        return this.processService(request);
    }

    protected abstract ResponseEntity<TResponse> processService(TRequest request);

}

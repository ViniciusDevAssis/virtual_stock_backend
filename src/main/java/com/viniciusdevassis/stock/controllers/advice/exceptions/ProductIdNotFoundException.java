package com.viniciusdevassis.stock.controllers.advice.exceptions;

import com.viniciusdevassis.stock.enums.Errors;

public class ProductIdNotFoundException extends RuntimeException {

    private final String errorCode;

    public ProductIdNotFoundException(Errors error, Object...args){
        super(error.formatMessage(args));
        this.errorCode = error.getCode();
    }

    public String getErrorCode(){
        return errorCode;
    }
}

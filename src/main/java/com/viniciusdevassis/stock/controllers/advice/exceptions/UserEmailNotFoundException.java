package com.viniciusdevassis.stock.controllers.advice.exceptions;

import com.viniciusdevassis.stock.enums.Errors;

public class UserEmailNotFoundException extends RuntimeException {

    private final String errorCode;

    public UserEmailNotFoundException(Errors error, Object...args){
        super(error.formatMessage(args));
        this.errorCode = error.getCode();
    }

    public String getErrorCode(){
        return errorCode;
    }
}

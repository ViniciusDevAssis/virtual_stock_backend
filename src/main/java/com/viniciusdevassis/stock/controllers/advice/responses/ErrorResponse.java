package com.viniciusdevassis.stock.controllers.advice.responses;

import java.util.List;

public class ErrorResponse {

    private final int httpCode;
    private final String message;
    private final String internalCode;
    private final List<FieldErrorResponse> errors;

    public ErrorResponse(int httpCode, String message, String internalCode, List<FieldErrorResponse> errors) {
        this.httpCode = httpCode;
        this.message = message;
        this.internalCode = internalCode;
        this.errors = errors;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getMessage() {
        return message;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public List<FieldErrorResponse> getErrors() {
        return errors;
    }
}

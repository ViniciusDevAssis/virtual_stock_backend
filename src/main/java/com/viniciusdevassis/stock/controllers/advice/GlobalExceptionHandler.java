package com.viniciusdevassis.stock.controllers.advice;

import com.viniciusdevassis.stock.controllers.advice.exceptions.ProductNotFoundException;
import com.viniciusdevassis.stock.controllers.advice.exceptions.UserNotFoundException;
import com.viniciusdevassis.stock.controllers.advice.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
            UserNotFoundException ex,
            WebRequest request
    ){
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                ex.getErrorCode(),
                null
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(
            ProductNotFoundException ex,
            WebRequest request
    ){
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                ex.getErrorCode(),
                null
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}

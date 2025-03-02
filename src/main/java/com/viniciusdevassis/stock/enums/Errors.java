package com.viniciusdevassis.stock.enums;

public enum Errors {

    // UEE-1XX for user entity errors
    UEE101("UEE-101", "Não existe nenhum usuário com o id informado!"),
    UEE102("UEE-102", "Nenhum usuário encontrado com o email informado!");

    private final String code;
    private final String message;

    Errors(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String formatMessage(Object... args){
        return String.format(message, args);
    }
}

package com.viniciusdevassis.stock.enums;

public enum Errors {

    // SNE-0XX para Spring native errors
    SNE001("SNE-001", "Não é possível registrar o usuário porque há campos vazios ou inválidos"),

    // UEE-1XX para user entity errors
    UEE101("UEE-101", "Nenhum usuário encontrado com o id informado!"),
    UEE102("UEE-102", "Nenhum usuário encontrado com o email informado!"),

    // PEE-2XX para product entity errors
    PEE201("PEE-201", "Nenhum produto encontrado com o id informado!"),
    PEE202("PEE-202", "Nenhum produto encontrado com o nome informado!");

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

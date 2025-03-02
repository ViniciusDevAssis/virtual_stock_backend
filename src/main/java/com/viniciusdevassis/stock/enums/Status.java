package com.viniciusdevassis.stock.enums;

public enum Status {
    ATIVO("ATIVO"),
    INATIVO("INATIVO");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Status fromString(String value) {
        for (Status status : Status.values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status não encontrado para o valor: " + value);
    }
}

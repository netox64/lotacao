package com.oficinadobrito.api.utils.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
    EM_ANDAMENTO,CONCLUIDA,FECHADA;

    @JsonCreator
    public static Status fromString(String value) {
        if (value != null) {
            return switch (value.toUpperCase()) {
                case "EM_ANDAMENTO" -> EM_ANDAMENTO;
                case "CONCLUIDA" -> CONCLUIDA;
                case "FECHADA" -> FECHADA;
                default -> throw new IllegalArgumentException("Unknown status: " + value);
            };
        }
        throw new IllegalArgumentException("Role cannot be null");
    }
}

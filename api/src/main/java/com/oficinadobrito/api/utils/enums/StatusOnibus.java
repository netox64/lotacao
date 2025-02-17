package com.oficinadobrito.api.utils.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusOnibus {
    APTO,QUEBRADO,NO_CONCERTO,LOTADO_DE_RAPARIGA;

    @JsonCreator
    public static StatusOnibus fromString(String value) {
        if (value != null) {
            return switch (value.toUpperCase()) {
                case "APTO" -> APTO;
                case "QUEBRADO" -> QUEBRADO;
                case "NO_CONCERTO" -> NO_CONCERTO;
                case "LOTADO_DE_RAPARIGA" -> LOTADO_DE_RAPARIGA;
                default -> throw new IllegalArgumentException("Unknown status onibus: " + value);
            };
        }
        throw new IllegalArgumentException("Role cannot be null");
    }
}

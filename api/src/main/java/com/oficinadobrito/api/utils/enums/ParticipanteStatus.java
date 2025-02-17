package com.oficinadobrito.api.utils.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ParticipanteStatus {
    PENDENTE,DESISTENTE,CONFIRMADO;

    @JsonCreator
    public static ParticipanteStatus fromString(String value) {
        if (value != null) {
            return switch (value.toUpperCase()) {
                case "PENDENTE" -> PENDENTE;
                case "DESISTENTE" -> DESISTENTE;
                case "CONFIRMADO" -> CONFIRMADO;
                default -> throw new IllegalArgumentException("Unknown role: " + value);
            };
        }
        throw new IllegalArgumentException("Role cannot be null");
    }
}

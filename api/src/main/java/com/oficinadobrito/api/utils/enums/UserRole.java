package com.oficinadobrito.api.utils.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserRole {
  PARTICIPANTE,ADMIN, MOTORISTA, ORGANIZADOR;

    @JsonCreator
    public static UserRole fromString(String value) {
        if (value != null) {
            return switch (value.toUpperCase()) {
                case "PARTICIPANTE" -> PARTICIPANTE;
                case "ADMIN" -> ADMIN;
                case "MOTORISTA" -> MOTORISTA;
                case "ORGANIZADOR" -> ORGANIZADOR;
                
                default -> throw new IllegalArgumentException("Unknown role: " + value);
            };
        }
        throw new IllegalArgumentException("Role cannot be null");
    }
}

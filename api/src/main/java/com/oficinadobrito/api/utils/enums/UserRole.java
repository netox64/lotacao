package com.oficinadobrito.api.utils.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserRole {
    ADMIN, USER, VENDEDOR, GERENTE;

    @JsonCreator
    public static UserRole fromString(String value) {
        if (value != null) {
            return switch (value.toUpperCase()) {
                case "ADMIN" -> ADMIN;
                case "USER" -> USER;
                case "VENDEDOR" -> VENDEDOR;
                case "GERENTE" -> GERENTE;
                default -> throw new IllegalArgumentException("Unknown role: " + value);
            };
        }
        throw new IllegalArgumentException("Role cannot be null");
    }
}

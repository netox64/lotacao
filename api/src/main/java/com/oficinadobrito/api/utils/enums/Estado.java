package com.oficinadobrito.api.utils.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Estado {
    AC, AL, AP, AM, BA, CE, DF, ES, GO, MA, MT, MS, MG, PA, PB, PR, PE, PI, RJ, RN, RS, RO, RR, SC, SP, SE, TO;
    @JsonCreator
    public static Estado fromString(String value) {
        if (value != null) {
            return switch (value.toUpperCase()) {
                case "AC" -> AC;
                case "AL" -> AL;
                case "AP" -> AP;
                case "AM" -> AM;
                case "BA" -> BA;
                case "CE" -> CE;
                case "DF" -> DF;
                case "ES" -> ES;
                case "GO" -> GO;
                case "MA" -> MA;
                case "MT" -> MT;
                case "MS" -> MS;
                case "MG" -> MG;
                case "PA" -> PA;
                case "PB" -> PB;
                case "PR" -> PR;
                case "PE" -> PE;
                case "PI" -> PI;
                case "RJ" -> RJ;
                case "RN" -> RN;
                case "RS" -> RS;
                case "RO" -> RO;
                case "RR" -> RR;
                case "SC" -> SC;
                case "SP" -> SP;
                case "SE" -> SE;
                case "TO" -> TO;
                default -> throw new IllegalArgumentException("Unknown status: " + value);
            };
        }
        throw new IllegalArgumentException("Role cannot be null");
    }
}

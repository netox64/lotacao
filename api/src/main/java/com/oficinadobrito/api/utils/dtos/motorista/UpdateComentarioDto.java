package com.oficinadobrito.api.utils.dtos.motorista;

import java.math.BigInteger;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateComentarioDto(
    @Size(min = 11, max = 11, message = "The CNH must have exactly 11 characters.") String CNH,
    Set<@NotNull(message = "Caravan ID cannot be null.") BigInteger> caravanasIds) {
}

package com.oficinadobrito.api.utils.dtos.motorista;

import java.math.BigInteger;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateMotoristaDto(
    @NotBlank(message = "The driver's license (CNH) is required.") @Size(min = 11, max = 11, message = "The CNH must have exactly 11 characters.") String CNH,
    Set<@NotNull(message = "Caravan ID cannot be null.") BigInteger> caravanasIds

) {
}

package com.oficinadobrito.api.utils.dtos.onibus;

import java.math.BigInteger;
import java.util.Set;
import com.oficinadobrito.api.utils.enums.StatusOnibus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateOnibusDto(
    @NotBlank(message = "The bus information cannot be empty.")
    @Size(max = 255, message = "The information can have a maximum of 255 characters.")
    String info,

    @NotBlank(message = "The license plate is required.")
    @Pattern(regexp = "^[A-Z]{3}\\d{4}$", message = "The license plate must follow the format ABC1234.")
    String placa,

    @NotNull(message = "The bus status is required.")
    StatusOnibus status,

    Set<@NotNull(message = "Caravan ID cannot be null.") BigInteger> caravanasIds
) {
}

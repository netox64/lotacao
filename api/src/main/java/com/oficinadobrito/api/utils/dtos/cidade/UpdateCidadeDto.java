package com.oficinadobrito.api.utils.dtos.cidade;

import java.math.BigInteger;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oficinadobrito.api.utils.enums.Estado;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateCidadeDto(
    @Size(max = 100, message = "The city name can have a maximum of 100 characters.") 
    String nome,
    Estado estado,
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90.") @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90.") 
    Double latitude,
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180.") @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180.") 
    Double longitude,
    Set<@NotNull(message = "Caravan ID cannot be null.") BigInteger> caravanasIds) {
}

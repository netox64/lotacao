package com.oficinadobrito.api.utils.dtos.caravana;

import jakarta.validation.constraints.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateCaravanaDto(
        @FutureOrPresent(message = "The departure date must be in the present or future.")
        LocalDate dataSaida,

        @Future(message = "The return date must be in the future.")
        LocalDate dataRetorno,

        @Min(value = 1, message = "The number of seats must be at least 1.")
        Integer quantVagas,

        @Size(max = 255, message = "The description can have a maximum of 255 characters.")
        String descricao,

        @DecimalMin(value = "0.0", inclusive = false, message = "The price per person must be greater than zero.")
        Double precoPorPessoa,

        Set<@NotNull(message = "City ID cannot be null.")BigInteger> cidadadesIds,

        String organizadorId,

        Set<@NotBlank(message = "The driver ID cannot be empty.") String> motoristasIds
) { }

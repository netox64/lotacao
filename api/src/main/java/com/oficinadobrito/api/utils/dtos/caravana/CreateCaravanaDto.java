package com.oficinadobrito.api.utils.dtos.caravana;
import jakarta.validation.constraints.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

public record CreateCaravanaDto(
        @NotNull(message = "The departure date is required.")
        @FutureOrPresent(message = "The departure date must be in the present or future.")
        LocalDate dataSaida,

        @NotNull(message = "The return date is required.")
        @Future(message = "The return date must be in the future.")
        LocalDate dataRetorno,

        @Min(value = 1, message = "The number of seats must be at least 1.")
        int quantVagas,

        @NotBlank(message = "The description cannot be empty.")
        @Size(max = 255, message = "The description can have a maximum of 255 characters.")
        String descricao,

        @DecimalMin(value = "0.0", inclusive = false, message = "The price per person must be greater than zero.")
        double precoPorPessoa,

        @NotEmpty(message = "There must be at least one destination city.")
        Set<@NotNull(message = "City ID cannot be null.") BigInteger> cidadadesIds,

        @NotBlank(message = "The organizer ID is required.")
        String organizadorId,

        @NotEmpty(message = "There must be at least one driver.")
        Set<@NotBlank(message = "The driver ID cannot be empty.") String> motoristasIds
) { }

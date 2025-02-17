package com.oficinadobrito.api.utils.dtos.comentario;

import java.math.BigInteger;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateComentarioDto(
    @NotBlank(message = "The comment text cannot be empty.") @Size(max = 500, message = "The comment text can have a maximum of 500 characters.") String texto,

    @NotNull(message = "The post ID is required.") BigInteger postId,

    @NotBlank(message = "The user ID is required.") String usuarioId) {
}

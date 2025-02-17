package com.oficinadobrito.api.utils.dtos.postagem;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;
import com.oficinadobrito.api.entities.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record CreatePostagemDto(
    @NotBlank(message = "The post content cannot be empty.") @Size(max = 500, message = "The content can have a maximum of 500 characters.") String conteudo,
    @NotNull(message = "The posting date is required.") @PastOrPresent(message = "The posting date cannot be in the future.") LocalDate dataPostagem,
    @NotNull(message = "The user is required.") Usuario usuario,
    @NotNull(message = "The caravan ID is required.") BigInteger caravanaId,
    Set<@NotNull(message = "Comment ID cannot be null.") BigInteger> comentariosIds) {
}

package com.oficinadobrito.api.utils.dtos.postagem;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oficinadobrito.api.entities.Usuario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdatePostagemDto(
    @Size(max = 500, message = "The content can have a maximum of 500 characters.") String conteudo,
    @PastOrPresent(message = "The posting date cannot be in the future.") LocalDate dataPostagem,
    Usuario usuario,
    BigInteger caravanaId,
    Set<@NotNull(message = "Comment ID cannot be null.") BigInteger> comentariosIds) {
}

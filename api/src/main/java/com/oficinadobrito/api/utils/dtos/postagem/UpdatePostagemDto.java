package com.oficinadobrito.api.utils.dtos.postagem;

import java.math.BigInteger;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdatePostagemDto(
    @Size(max = 500, message = "The content can have a maximum of 500 characters.") 
    String conteudo,
    BigInteger caravanaId,
    Set<@NotNull(message = "Comment ID cannot be null.") BigInteger> comentariosIds
) {
}

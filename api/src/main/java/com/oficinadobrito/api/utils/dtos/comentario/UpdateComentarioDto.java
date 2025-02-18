package com.oficinadobrito.api.utils.dtos.comentario;


import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateComentarioDto(
    @Size(max = 500, message = "The comment text can have a maximum of 500 characters.") String texto
) { }

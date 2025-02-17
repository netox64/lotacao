package com.oficinadobrito.api.utils.dtos.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record CreateUsuarioDto(
        @NotEmpty(message = "property 'username' cannot be null, is required a value")
        String username,
        @Email(message = "property 'email' cannot be null, is required a value, a valid email")
        String email,
        @NotEmpty(message = "property 'phone' cannot be null, is required a value")
        String phone,
        @NotEmpty(message = "property 'cpf' cannot be null, is required a value")
        @Size(min = 11, max = 14, message = "cpf must be between 2 and 50 characters")
        @CPF(message = "property 'cpf' must be a valid CPF")
        String cpf,
        @NotEmpty(message = "property 'Password' cannot be null, is required a value")
        String password
) {
}

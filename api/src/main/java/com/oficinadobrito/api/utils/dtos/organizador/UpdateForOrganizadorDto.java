package com.oficinadobrito.api.utils.dtos.organizador;

import com.oficinadobrito.api.utils.enums.UserRole;

public record UpdateForOrganizadorDto(
  UserRole role
) {
}

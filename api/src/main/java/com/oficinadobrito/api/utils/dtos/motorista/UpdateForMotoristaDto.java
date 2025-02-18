package com.oficinadobrito.api.utils.dtos.motorista;

import com.oficinadobrito.api.utils.enums.UserRole;

public record UpdateForMotoristaDto(
  String CNH,
  UserRole role
) {
}

package com.oficinadobrito.api.utils.dtos.participante;

import com.oficinadobrito.api.utils.enums.UserRole;

public record UpdateForParticipanteDto(
  UserRole role
) {
}

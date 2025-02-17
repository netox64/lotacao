package com.oficinadobrito.api.repositories;

import com.oficinadobrito.api.entities.Participante;
import com.oficinadobrito.api.repositories.generics.GenericUsersRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepository extends GenericUsersRepository<Participante> {
}

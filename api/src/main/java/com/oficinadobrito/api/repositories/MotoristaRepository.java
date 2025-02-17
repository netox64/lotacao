package com.oficinadobrito.api.repositories;

import com.oficinadobrito.api.entities.Motorista;
import com.oficinadobrito.api.repositories.generics.GenericUsersRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoristaRepository extends GenericUsersRepository<Motorista> {
}

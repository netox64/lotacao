package com.oficinadobrito.api.repositories;

import com.oficinadobrito.api.entities.Organizador;
import com.oficinadobrito.api.repositories.generics.GenericUsersRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizadorRepository extends GenericUsersRepository<Organizador> {
}

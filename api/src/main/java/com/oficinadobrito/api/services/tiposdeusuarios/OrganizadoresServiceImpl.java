package com.oficinadobrito.api.services.tiposdeusuarios;

import com.oficinadobrito.api.entities.Organizador;
import com.oficinadobrito.api.repositories.generics.GenericUsersRepository;
import com.oficinadobrito.api.services.generics.GenericUsersServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrganizadoresServiceImpl extends GenericUsersServiceImpl<Organizador> {
  public OrganizadoresServiceImpl(GenericUsersRepository<Organizador> repository) {
    super(repository);
  }
}

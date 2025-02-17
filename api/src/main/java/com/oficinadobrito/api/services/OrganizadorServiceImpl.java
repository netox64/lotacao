package com.oficinadobrito.api.services;

import com.oficinadobrito.api.entities.Organizador;
import com.oficinadobrito.api.repositories.generics.GenericUsersRepository;
import com.oficinadobrito.api.services.generics.GenericUsersServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrganizadorServiceImpl extends GenericUsersServiceImpl<Organizador> {
  
  public OrganizadorServiceImpl(GenericUsersRepository<Organizador> repository) {
    super(repository);
  }
  
}

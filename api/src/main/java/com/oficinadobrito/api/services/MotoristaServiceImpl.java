package com.oficinadobrito.api.services;

import com.oficinadobrito.api.entities.Motorista;
import com.oficinadobrito.api.repositories.generics.GenericUsersRepository;
import com.oficinadobrito.api.services.generics.GenericUsersServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MotoristaServiceImpl extends GenericUsersServiceImpl<Motorista> {
  
  public MotoristaServiceImpl(GenericUsersRepository<Motorista> repository) {
    super(repository);
  }
}

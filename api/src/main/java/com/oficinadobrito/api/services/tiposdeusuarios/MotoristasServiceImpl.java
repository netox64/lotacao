package com.oficinadobrito.api.services.tiposdeusuarios;

import com.oficinadobrito.api.entities.Motorista;
import com.oficinadobrito.api.repositories.generics.GenericUsersRepository;
import com.oficinadobrito.api.services.generics.GenericUsersServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MotoristasServiceImpl extends GenericUsersServiceImpl<Motorista> {
  public MotoristasServiceImpl(GenericUsersRepository<Motorista> repository) {
    super(repository);
  }
}

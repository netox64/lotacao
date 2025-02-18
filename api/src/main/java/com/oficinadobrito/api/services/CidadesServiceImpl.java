package com.oficinadobrito.api.services;

import com.oficinadobrito.api.entities.Cidade;
import com.oficinadobrito.api.repositories.generics.GenericRepository;
import com.oficinadobrito.api.services.generics.GenericServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class CidadesServiceImpl extends GenericServiceImpl<Cidade> {
  
  public CidadesServiceImpl(GenericRepository<Cidade> repository) {
    super(repository);
  }
  
}

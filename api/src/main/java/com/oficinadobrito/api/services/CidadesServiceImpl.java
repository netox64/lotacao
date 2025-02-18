package com.oficinadobrito.api.services;

import com.oficinadobrito.api.entities.Caravana;
import com.oficinadobrito.api.entities.Cidade;
import com.oficinadobrito.api.repositories.generics.GenericRepository;
import com.oficinadobrito.api.services.generics.GenericServiceImpl;
import com.oficinadobrito.api.utils.dtos.cidade.UpdateCidadeDto;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CidadesServiceImpl extends GenericServiceImpl<Cidade> {
  
  public CidadesServiceImpl(GenericRepository<Cidade> repository) {
    super(repository);
  }
  
}

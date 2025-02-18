package com.oficinadobrito.api.services;

import com.oficinadobrito.api.entities.Comentario;
import com.oficinadobrito.api.repositories.generics.GenericRepository;
import com.oficinadobrito.api.services.generics.GenericServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class ComentariosServiceImpl  extends GenericServiceImpl<Comentario> {
  
  public ComentariosServiceImpl(GenericRepository<Comentario> repository){
    super(repository);
  }
  
}

package com.oficinadobrito.api.services.tiposdeusuarios;

import com.oficinadobrito.api.entities.Participante;
import com.oficinadobrito.api.entities.Usuario;
import com.oficinadobrito.api.repositories.generics.GenericUsersRepository;
import com.oficinadobrito.api.services.generics.GenericUsersServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ParticipantesServiceImpl extends GenericUsersServiceImpl<Participante> {
  
  public ParticipantesServiceImpl(GenericUsersRepository<Participante> repository) {
    super(repository);
  }

  public Participante create(Usuario user) {
    Participante participante = new Participante(user.getUsername(), user.getEmail(), user.getPhone(), user.getCpf(), user.getPassword());
    return this.save(participante);
  }
}

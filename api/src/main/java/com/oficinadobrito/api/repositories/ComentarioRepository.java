package com.oficinadobrito.api.repositories;

import com.oficinadobrito.api.entities.Comentario;
import com.oficinadobrito.api.repositories.generics.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends GenericRepository<Comentario> {
}

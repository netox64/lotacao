package com.oficinadobrito.api.repositories;

import com.oficinadobrito.api.entities.Postagem;
import com.oficinadobrito.api.repositories.generics.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends GenericRepository<Postagem> {
}

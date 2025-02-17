package com.oficinadobrito.api.repositories;

import com.oficinadobrito.api.entities.Cidade;
import com.oficinadobrito.api.repositories.generics.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends GenericRepository<Cidade> {
}

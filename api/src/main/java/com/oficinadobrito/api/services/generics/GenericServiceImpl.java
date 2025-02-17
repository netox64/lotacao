package com.oficinadobrito.api.services.generics;

import com.oficinadobrito.api.configs.exeptions.ResourceNotFoundException;
import com.oficinadobrito.api.repositories.generics.GenericRepository;
import com.oficinadobrito.api.services.interfaces.GenericService;

import java.math.BigInteger;

public abstract class GenericServiceImpl<T> implements GenericService<T, BigInteger> {

    private static final String MESSAGE = "The searched resource containing this id does not exist.";
    protected final GenericRepository<T> repository;

    public GenericServiceImpl( GenericRepository<T> repository){
      this.repository = repository;
    }

    @Override
    public T save(T resource) {
        return this.repository.save(resource);
    }

    @Override
    public T findById(BigInteger id) {
        return this.repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(MESSAGE));
    }

    @Override
    public Iterable<T> findAll() {
      return this.repository.findAll();
    }

    @Override
    public void delete(BigInteger id) {
        this.repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(MESSAGE));
        this.repository.deleteById(id);
    }
}

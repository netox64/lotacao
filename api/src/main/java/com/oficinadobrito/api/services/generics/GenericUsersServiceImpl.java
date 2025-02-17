package com.oficinadobrito.api.services.generics;

import com.oficinadobrito.api.configs.exeptions.ResourceNotFoundException;
import com.oficinadobrito.api.repositories.generics.GenericUsersRepository;
import com.oficinadobrito.api.services.interfaces.GenericService;

public abstract class GenericUsersServiceImpl<T> implements GenericService<T, String> {

    private static final String MESSAGE = "The searched resource containing this id does not exist.";
    protected final GenericUsersRepository<T> repository;

    public GenericUsersServiceImpl(GenericUsersRepository<T> repository){
      this.repository = repository;
    }

    @Override
    public T save(T resource) {
        return this.repository.save(resource);
    }

    @Override
    public T findById(String id) {
        return this.repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(MESSAGE));
    }

    @Override
    public Iterable<T> findAll() {
        return this.repository.findAll();
    }

    @Override
    public void delete(String id) {
        this.repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(MESSAGE));
        this.repository.deleteById(id);
    }
}

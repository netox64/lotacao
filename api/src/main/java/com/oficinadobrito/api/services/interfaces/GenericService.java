package com.oficinadobrito.api.services.interfaces;

public interface GenericService<T,ID> {
    T save(T resource);
    T findById(ID id);
    Iterable<T> findAll();
    void delete(ID id);
}

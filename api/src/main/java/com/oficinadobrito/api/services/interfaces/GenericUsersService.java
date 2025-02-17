package com.oficinadobrito.api.services.interfaces;

import java.io.Serializable;

public interface GenericUsersService<T,ID extends Serializable> {
    T save(T resource);
    T findById(ID id);
    Iterable<T> findAll();
    void delete(ID id);
}

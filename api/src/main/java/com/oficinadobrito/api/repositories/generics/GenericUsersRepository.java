package com.oficinadobrito.api.repositories.generics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericUsersRepository <T> extends JpaRepository<T, String> {
}

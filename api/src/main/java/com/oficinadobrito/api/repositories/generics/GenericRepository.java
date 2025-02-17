package com.oficinadobrito.api.repositories.generics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.math.BigInteger;

@NoRepositoryBean
public interface GenericRepository<T> extends JpaRepository<T, BigInteger> {
}

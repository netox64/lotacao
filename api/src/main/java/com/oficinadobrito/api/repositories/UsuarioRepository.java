package com.oficinadobrito.api.repositories;

import com.oficinadobrito.api.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {
}

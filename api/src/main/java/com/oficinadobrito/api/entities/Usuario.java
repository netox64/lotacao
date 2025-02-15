package com.oficinadobrito.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@Table(name = "tb_usuarios")
public class Usuario {

    @Id
    @UuidGenerator
    private String usuarioId;

}

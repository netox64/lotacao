package com.oficinadobrito.api.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Data
@Entity
@Table(name = "tb_comentarios")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger comentarioId;

    private String texto;

    @ManyToOne
    @JoinColumn(name = "postagemId")
    private Postagem post;

    private Usuario usuario;
}

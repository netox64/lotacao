package com.oficinadobrito.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tb_postagens")
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger postagemId;
    
    private String conteudo;
    
    private LocalDate dataPostagem;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "caravanaId")
    private Caravana caravana;

    @OneToMany(mappedBy = "post")
    private Set<Comentario> comentarios;

    public  Postagem(){
        this.comentarios = new HashSet<>();
    }


}

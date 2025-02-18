package com.oficinadobrito.api.entities;

import com.oficinadobrito.api.utils.dtos.comentario.CreateComentarioDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
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
    
    public Comentario(){}
    
  public static  Comentario createEntityToDto(CreateComentarioDto comentarioDto){
      Comentario comentario = new Comentario();
      comentario.setTexto(comentarioDto.texto());
      return comentario;
  }
}

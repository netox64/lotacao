package com.oficinadobrito.api.services;

import com.oficinadobrito.api.entities.Caravana;
import com.oficinadobrito.api.entities.Comentario;
import com.oficinadobrito.api.entities.Postagem;
import com.oficinadobrito.api.entities.Usuario;
import com.oficinadobrito.api.repositories.generics.GenericRepository;
import com.oficinadobrito.api.services.generics.GenericServiceImpl;
import com.oficinadobrito.api.services.usuario.UsuariosService;
import com.oficinadobrito.api.utils.dtos.comentario.UpdateComentarioDto;
import com.oficinadobrito.api.utils.dtos.postagem.UpdatePostagemDto;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Set;

@Service
public class PostagensServiceImpl extends GenericServiceImpl<Postagem> {
  
  private final UsuariosService usuariosService;
  private final CaravanasServiceImpl caravanasService;
  private final ComentariosServiceImpl comentariosService;
  
  public PostagensServiceImpl(GenericRepository<Postagem> repository,UsuariosService usuariosService,CaravanasServiceImpl caravanasService,ComentariosServiceImpl comentariosService) {
    super(repository);
    this.usuariosService = usuariosService;
    this.caravanasService = caravanasService;
    this.comentariosService = comentariosService;
  }

  public Postagem create(Postagem postagem,  String usuarioId, BigInteger caravanaId, Set<BigInteger> comentariosIds) {
    Usuario usuario = this.usuariosService.findUsuarioById(usuarioId);
    postagem.setUsuario(usuario);
    Caravana caravana = this.caravanasService.findById(caravanaId);
    postagem.setCaravana(caravana);
    comentariosIds.forEach(comentarioId -> postagem.addComentario(this.comentariosService.findById(comentarioId)));
    return this.save(postagem);
  }

  public Postagem update(BigInteger postId, UpdatePostagemDto resource) {
    Postagem postagem = this.findById(postId);
    
    if (resource.conteudo() != null) {
      postagem.setConteudo(resource.conteudo());
    }
    
    if (resource.caravanaId() != null) {
      Caravana caravana = this.caravanasService.findById(resource.caravanaId());
      postagem.setCaravana(caravana);
    }
    
    if (resource.comentariosIds() != null && !resource.comentariosIds().isEmpty()) {
      postagem.getComentarios().clear();
      resource.comentariosIds().forEach(comentarioId ->
        postagem.addComentario(this.comentariosService.findById(comentarioId))
      );
    }

    return this.save(postagem);
  }
  public Comentario createComentario(Comentario comentario, BigInteger postId, String usuarioId) {
    Postagem postagem = this.findById(postId);
    Usuario usuario = this.usuariosService.findUsuarioById(usuarioId);
    comentario.setPost(postagem);
    comentario.setUsuario(usuario);
    return this.comentariosService.save(comentario);
  }

  public Comentario updateComentario(BigInteger comentarioId, UpdateComentarioDto comentarioDto) {
    Comentario comentario = this.comentariosService.findById(comentarioId);
    if(comentarioDto.texto() != null){
      comentario.setTexto(comentarioDto.texto());
    }
    return this.comentariosService.save(comentario);
  }
}

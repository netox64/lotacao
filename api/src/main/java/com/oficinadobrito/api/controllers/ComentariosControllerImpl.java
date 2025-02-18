package com.oficinadobrito.api.controllers;

import com.oficinadobrito.api.controllers.interfaces.Controller;
import com.oficinadobrito.api.entities.Comentario;
import com.oficinadobrito.api.services.ComentariosServiceImpl;
import com.oficinadobrito.api.services.PostagensServiceImpl;
import com.oficinadobrito.api.utils.dtos.comentario.CreateComentarioDto;
import com.oficinadobrito.api.utils.dtos.comentario.UpdateComentarioDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/comentarios")
public class ComentariosControllerImpl implements Controller<Comentario,CreateComentarioDto,UpdateComentarioDto,BigInteger> {
  
  private final ComentariosServiceImpl comentariosService;
  private final PostagensServiceImpl postagensService;
  
  public ComentariosControllerImpl(ComentariosServiceImpl comentariosService,PostagensServiceImpl postagensService){
    this.comentariosService = comentariosService;
    this.postagensService = postagensService;
  }
  
  @PostMapping()
  @Override
  public ResponseEntity<Comentario> postResource(@RequestBody @Valid CreateComentarioDto resource) {
    Comentario cidade = this.postagensService.createComentario(Comentario.createEntityToDto(resource),resource.postId(),resource.usuarioId());
    return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
  }

  @GetMapping("/{id}")
  @Override
  public ResponseEntity<Comentario> getResourceById(@PathVariable("id") BigInteger comentarioId) {
    Comentario cidade = this.comentariosService.findById(comentarioId);
    return ResponseEntity.ok(cidade);
  }

  @GetMapping()
  @Override
  public ResponseEntity<List<Comentario>> getAllResource() {
    List<Comentario> cidades = StreamSupport.stream(this.comentariosService.findAll().spliterator(),false).toList();
    return ResponseEntity.ok(cidades);
  }

  @PutMapping("/{id}")
  @Override
  public ResponseEntity<Comentario> updateResource(@PathVariable("id") BigInteger comentarioId, UpdateComentarioDto resource) {
    Comentario cidade = this.postagensService.updateComentario(comentarioId,resource);
    return ResponseEntity.ok(cidade);
  }

  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<Void> deleteResourceById(@PathVariable("id") BigInteger comentarioId) {
    this.comentariosService.delete(comentarioId);
    return  ResponseEntity.ok().build();
  }
  
}

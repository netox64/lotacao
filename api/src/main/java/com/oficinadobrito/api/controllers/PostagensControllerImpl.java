package com.oficinadobrito.api.controllers;

import com.oficinadobrito.api.controllers.interfaces.Controller;
import com.oficinadobrito.api.entities.Postagem;
import com.oficinadobrito.api.services.PostagensServiceImpl;
import com.oficinadobrito.api.utils.dtos.postagem.CreatePostagemDto;
import com.oficinadobrito.api.utils.dtos.postagem.UpdatePostagemDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/postagens")
public class PostagensControllerImpl implements Controller<Postagem, CreatePostagemDto, UpdatePostagemDto, BigInteger> {
  private final PostagensServiceImpl postagensService;
  public PostagensControllerImpl(PostagensServiceImpl postagensService){
    this.postagensService = postagensService;
  }
  @PostMapping()
  @Override
  public ResponseEntity<Postagem> postResource(@RequestBody @Valid CreatePostagemDto resource) {
    Postagem cidade = this.postagensService.create(Postagem.createEntityToDto(resource),resource.usuarioId(),resource.caravanaId(),resource.comentariosIds());
    return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
  }

  @GetMapping("/{id}")
  @Override
  public ResponseEntity<Postagem> getResourceById(@PathVariable("id") BigInteger postId) {
    Postagem cidade = this.postagensService.findById(postId);
    return ResponseEntity.ok(cidade);
  }

  @GetMapping()
  @Override
  public ResponseEntity<List<Postagem>> getAllResource() {
    List<Postagem> cidades = StreamSupport.stream(this.postagensService.findAll().spliterator(),false).toList();
    return ResponseEntity.ok(cidades);
  }

  @PutMapping("/{id}")
  @Override
  public ResponseEntity<Postagem> updateResource(@PathVariable("id") BigInteger postId, UpdatePostagemDto resource) {
    Postagem cidade = this.postagensService.update(postId,resource);
    return ResponseEntity.ok(cidade);
  }

  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<Void> deleteResourceById(@PathVariable("id") BigInteger postId) {
    this.postagensService.delete(postId);
    return  ResponseEntity.ok().build();
  }
}

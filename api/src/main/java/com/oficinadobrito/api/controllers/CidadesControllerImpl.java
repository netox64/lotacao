package com.oficinadobrito.api.controllers;

import com.oficinadobrito.api.controllers.interfaces.Controller;
import com.oficinadobrito.api.entities.Cidade;
import com.oficinadobrito.api.services.CaravanasServiceImpl;
import com.oficinadobrito.api.services.CidadesServiceImpl;
import com.oficinadobrito.api.utils.dtos.cidade.CreateCidadeDto;
import com.oficinadobrito.api.utils.dtos.cidade.UpdateCidadeDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/cidades")
public class CidadesControllerImpl implements Controller<Cidade, CreateCidadeDto, UpdateCidadeDto, BigInteger> {
  
  private final CaravanasServiceImpl caravanasService;
  private final CidadesServiceImpl cidadesService;
  
  public CidadesControllerImpl(CidadesServiceImpl cidadesService,CaravanasServiceImpl caravanasService){
    this.caravanasService = caravanasService;
    this.cidadesService =cidadesService;
  }
  
  @PostMapping()
  @Override
  public ResponseEntity<Cidade> postResource(@RequestBody @Valid CreateCidadeDto resource) {
    Cidade cidade = this.caravanasService.create(Cidade.createEntityToDto(resource),resource.caravanasIds());
    return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
  }
  
  @GetMapping("/{id}")
  @Override
  public ResponseEntity<Cidade> getResourceById(@PathVariable("id") BigInteger cidadeId) {
    Cidade cidade = this.cidadesService.findById(cidadeId);
    return ResponseEntity.ok(cidade);
  }

  @GetMapping()
  @Override
  public ResponseEntity<List<Cidade>> getAllResource() {
    List<Cidade> cidades = StreamSupport.stream(this.cidadesService.findAll().spliterator(),false).toList();
    return ResponseEntity.ok(cidades);
  }
  
  @PutMapping("/{id}")
  @Override
  public ResponseEntity<Cidade> updateResource(@PathVariable("id") BigInteger cidadeId, UpdateCidadeDto resource) {
    Cidade cidade = this.caravanasService.update(cidadeId,resource);
    return ResponseEntity.ok(cidade);
  }
  
  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<Void> deleteResourceById(@PathVariable("id") BigInteger cidadeId) {
    this.cidadesService.delete(cidadeId);
    return  ResponseEntity.ok().build();
  }
}

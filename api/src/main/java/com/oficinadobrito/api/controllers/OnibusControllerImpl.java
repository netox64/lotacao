package com.oficinadobrito.api.controllers;

import com.oficinadobrito.api.controllers.interfaces.Controller;
import com.oficinadobrito.api.entities.Onibus;
import com.oficinadobrito.api.services.OnibusServiceImpl;
import com.oficinadobrito.api.utils.dtos.onibus.CreateOnibusDto;
import com.oficinadobrito.api.utils.dtos.onibus.UpdateOnibusDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/onibus")
public class OnibusControllerImpl implements Controller<Onibus, CreateOnibusDto, UpdateOnibusDto, BigInteger> {
  
  private final OnibusServiceImpl onibusService;
  
  public OnibusControllerImpl( OnibusServiceImpl onibusService){
    this.onibusService = onibusService;
  }
  @PostMapping()
  @Override
  public ResponseEntity<Onibus> postResource(@RequestBody @Valid CreateOnibusDto resource) {
    Onibus cidade = this.onibusService.create(Onibus.createEntityToDto(resource),resource.caravanasIds());
    return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
  }

  @GetMapping("/{id}")
  @Override
  public ResponseEntity<Onibus> getResourceById(@PathVariable("id") BigInteger onibusId) {
    Onibus cidade = this.onibusService.findById(onibusId);
    return ResponseEntity.ok(cidade);
  }

  @GetMapping()
  @Override
  public ResponseEntity<List<Onibus>> getAllResource() {
    List<Onibus> cidades = StreamSupport.stream(this.onibusService.findAll().spliterator(),false).toList();
    return ResponseEntity.ok(cidades);
  }

  @PutMapping("/{id}")
  @Override
  public ResponseEntity<Onibus> updateResource(@PathVariable("id") BigInteger onibusId, UpdateOnibusDto resource) {
    Onibus cidade = this.onibusService.update(onibusId,resource);
    return ResponseEntity.ok(cidade);
  }

  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<Void> deleteResourceById(@PathVariable("id") BigInteger onibusId) {
    this.onibusService.delete(onibusId);
    return  ResponseEntity.ok().build();
  }
}

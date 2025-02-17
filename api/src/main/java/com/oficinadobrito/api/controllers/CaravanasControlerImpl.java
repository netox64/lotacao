package com.oficinadobrito.api.controllers;

import com.oficinadobrito.api.controllers.interfaces.Controller;
import com.oficinadobrito.api.entities.Caravana;
import com.oficinadobrito.api.services.CaravanasServiceImpl;
import com.oficinadobrito.api.utils.dtos.caravana.CreateCaravanaDto;
import com.oficinadobrito.api.utils.dtos.caravana.UpdateCaravanaDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/caravanas")
public class CaravanasControlerImpl implements Controller<Caravana, CreateCaravanaDto, UpdateCaravanaDto, BigInteger> {
  
    private final CaravanasServiceImpl caravanasService;
    
    public CaravanasControlerImpl(CaravanasServiceImpl caravanasService){
      this.caravanasService = caravanasService;
    }
    
    @PostMapping()
    @Override
    public ResponseEntity<Caravana> postResource(@RequestBody @Valid CreateCaravanaDto resource) {
      Caravana caravana = this.caravanasService.create(Caravana.createEntityToDto(resource),resource.cidadadesIds(),resource.organizadorId(),resource.motoristasIds());
      return ResponseEntity.status(HttpStatus.CREATED).body(caravana);
    }
  
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Caravana> getResourceById(@PathVariable("id") BigInteger id) {
        Caravana caravana = this.caravanasService.findById(id);
        return ResponseEntity.ok(caravana);
    }
    
    @GetMapping()
    @Override
    public ResponseEntity<List<Caravana>> getAllResource() {
      List<Caravana> caravanas = StreamSupport.stream(this.caravanasService.findAll().spliterator(),false).toList();
      return ResponseEntity.ok(caravanas);
    }
    
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Caravana> updateResource(@PathVariable("id") BigInteger id, UpdateCaravanaDto resource) {
        Caravana caravana = this.caravanasService.update(id,resource);
        return ResponseEntity.ok(caravana);
    }
    
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteResourceById(@PathVariable("id") BigInteger id) {
        this.caravanasService.delete(id);
        return ResponseEntity.ok().build();
    }
}

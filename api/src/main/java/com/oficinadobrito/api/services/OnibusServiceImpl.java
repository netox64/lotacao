package com.oficinadobrito.api.services;

import com.oficinadobrito.api.entities.Onibus;
import com.oficinadobrito.api.repositories.generics.GenericRepository;
import com.oficinadobrito.api.services.generics.GenericServiceImpl;
import com.oficinadobrito.api.utils.dtos.onibus.UpdateOnibusDto;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Set;

@Service
public class OnibusServiceImpl extends GenericServiceImpl<Onibus> {
  private final CaravanasServiceImpl caravanasService;
  
  public OnibusServiceImpl(GenericRepository<Onibus> repository,CaravanasServiceImpl caravanasService) {
    super(repository);
    this.caravanasService = caravanasService;
  }
  
  public Onibus create(Onibus onibus, Set<BigInteger> caravanasIds) {
    caravanasIds.forEach(caravanaId -> onibus.addCaravana(this.caravanasService.findById(caravanaId)));
    return this.save(onibus);
  }
  
  public Onibus update(BigInteger onibusId, UpdateOnibusDto onibusDto) {
    Onibus onibus = this.findById(onibusId);
    
    if (onibusDto.info() != null) {
      onibus.setInfo(onibusDto.info());
    }

    if (onibusDto.placa() != null) {
      onibus.setPlaca(onibusDto.placa());
    }

    if (onibusDto.status() != null) {
      onibus.setStatus(onibusDto.status());
    }

    if (onibusDto.caravanasIds() != null && !onibusDto.caravanasIds().isEmpty()) {
      onibus.getCaravanas().clear();
      onibusDto.caravanasIds().forEach(caravanaId ->
        onibus.addCaravana(this.caravanasService.findById(caravanaId))
      );
    }
    return this.save(onibus);
  }
}

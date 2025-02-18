package com.oficinadobrito.api.services;

import com.oficinadobrito.api.entities.Caravana;
import com.oficinadobrito.api.entities.Cidade;
import com.oficinadobrito.api.entities.Motorista;
import com.oficinadobrito.api.repositories.generics.GenericRepository;
import com.oficinadobrito.api.services.generics.GenericServiceImpl;
import com.oficinadobrito.api.utils.dtos.caravana.UpdateCaravanaDto;
import com.oficinadobrito.api.utils.dtos.cidade.UpdateCidadeDto;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CaravanasServiceImpl extends GenericServiceImpl<Caravana> {
  
  private final CidadesServiceImpl cidadesService;
  private  final OrganizadorServiceImpl organizadorService;
  private final MotoristaServiceImpl motoristaService;
  
  public CaravanasServiceImpl(GenericRepository<Caravana> repository, CidadesServiceImpl cidadesService, OrganizadorServiceImpl organizadorService, MotoristaServiceImpl motoristaService) {
    super(repository);
    this.cidadesService = cidadesService;
    this.organizadorService = organizadorService;
    this.motoristaService = motoristaService;
  }
  
  public Caravana create(Caravana caravana, Set<BigInteger> cidadesIds, String organizadorId, Set<String> motoristasIds) {
    cidadesIds.forEach(id -> caravana.addCidade(this.cidadesService.findById(id)));
    motoristasIds.forEach(id -> caravana.addMotorista(this.motoristaService.findById(id)));

    caravana.setOrganizador(this.organizadorService.findById(organizadorId));
    return this.save(caravana);
  }
  
  public Caravana update( BigInteger id, UpdateCaravanaDto resource){
    Caravana caravana = this.findById(id);
    caravana.setDataSaida(resource.dataSaida());
    caravana.setDataRetorno(resource.dataRetorno());
    caravana.setQuantVagas(resource.quantVagas());
    caravana.setDescricao(resource.descricao());
    caravana.setPrecoPorPessoa(resource.precoPorPessoa());
    
    Set<Cidade> cidades = resource.cidadadesIds().stream().map(this.cidadesService::findById).collect(Collectors.toSet());
    caravana.setCidades(cidades);
    
    Set<Motorista> motoristas = resource.motoristasIds().stream().map(this.motoristaService::findById).collect(Collectors.toSet());
    caravana.setMotoristas(motoristas);
    
    if (resource.organizadorId() != null) {
      caravana.setOrganizador(this.organizadorService.findById(resource.organizadorId()));
    }
    
    return this.save(caravana);
  }

  public Cidade create(Cidade cidade, Set<BigInteger> caravanasIds) {
    caravanasIds.forEach(id -> cidade.addCaravana(this.findById(id)));
    return this.cidadesService.save(cidade);
  }

  public Cidade update(BigInteger cidadeId, UpdateCidadeDto resource) {
    Cidade cidade = this.cidadesService.findById(cidadeId);
    if (resource.nome() != null) {
      cidade.setNome(resource.nome());
    }

    if (resource.estado() != null) {
      cidade.setEstado(resource.estado());
    }

    if (resource.latitude() != null) {
      cidade.setLatitude(resource.latitude());
    }

    if (resource.longitude() != null) {
      cidade.setLongitude(resource.longitude());
    }

    if (resource.caravanasIds() != null) {
      Set<Caravana> caravanas = resource.caravanasIds().stream().map(this::findById).collect(Collectors.toSet());
      cidade.setCaravanas(caravanas);
    }

    return this.cidadesService.save(cidade);
  }
  
}

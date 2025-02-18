package com.oficinadobrito.api.entities;

import com.oficinadobrito.api.utils.dtos.cidade.CreateCidadeDto;
import com.oficinadobrito.api.utils.enums.Estado;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_cidades")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger cidadeId;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private double latitude;

    private double longitude;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "cidade_caravana", joinColumns = { @JoinColumn(name = "cidadeId") }, inverseJoinColumns = { @JoinColumn(name = "caravanaId") })
    private Set<Caravana> caravanas;

    public Cidade(){
        this.caravanas = new HashSet<>();
    }
    
    public static Cidade createEntityToDto(CreateCidadeDto dto){
      Cidade cidade = new Cidade();
      cidade.setNome(dto.nome());
      cidade.setEstado(dto.estado());
      cidade.setLatitude(dto.latitude());
      cidade.setLongitude(dto.longitude());
      
      return cidade;
    }
    
    public void addCaravana(Caravana caravana){
      this.caravanas.add(caravana);
    }
}

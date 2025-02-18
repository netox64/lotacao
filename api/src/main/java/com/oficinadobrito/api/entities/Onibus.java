package com.oficinadobrito.api.entities;

import com.oficinadobrito.api.utils.dtos.onibus.CreateOnibusDto;
import com.oficinadobrito.api.utils.enums.StatusOnibus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tb_onibus")
public class Onibus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger onibusId;
    private String info;
    private String placa;
    private StatusOnibus status;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "onibus_caravana", joinColumns = { @JoinColumn(name = "onibusId") }, inverseJoinColumns = { @JoinColumn(name = "caravanaId") })
    private Set<Caravana> caravanas;

    public  Onibus(){
        this.caravanas = new HashSet<>();
    }
    
    public static  Onibus createEntityToDto(CreateOnibusDto onibusDto){
      Onibus onibus = new Onibus();
      onibus.setInfo(onibusDto.info());
      onibus.setPlaca(onibusDto.placa());
      onibus.setStatus(onibusDto.status());
      return onibus;
    }
    public void addCaravana(Caravana caravana){
      this.caravanas.add(caravana);
    }
}

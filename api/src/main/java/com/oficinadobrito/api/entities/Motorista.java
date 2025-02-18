package com.oficinadobrito.api.entities;

import com.oficinadobrito.api.utils.enums.UserRole;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Table(name = "tb_motoristas")
@PrimaryKeyJoinColumn(name = "usuarioId")
public class Motorista extends Usuario{
  
    private String CNH;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "motorista_caravana",joinColumns = { @JoinColumn(name = "usuarioId") }, inverseJoinColumns = { @JoinColumn(name = "caravanaId") })
    private Set<Caravana> caravanas;

    public Motorista(){
      this.caravanas = new HashSet<>();
    }
    
    public Motorista(String username, String email, String phone, String cpf, String password) {
      super(username, email, phone, cpf, password, UserRole.ORGANIZADOR);
      this.caravanas = new HashSet<>();
    }
    
    public void addCaravana(Caravana caravana){
      this.caravanas.add(caravana);
    }
}

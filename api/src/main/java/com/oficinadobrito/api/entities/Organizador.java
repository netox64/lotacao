package com.oficinadobrito.api.entities;

import com.oficinadobrito.api.utils.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tb_organizador")
@PrimaryKeyJoinColumn(name = "usuarioId")
public class Organizador extends Usuario{

    @OneToMany(mappedBy = "organizador")
    private Set<Caravana> caravanas;

    public Organizador(){
        this.caravanas = new HashSet<>();
    }
  public Organizador(String username, String email, String phone, String cpf, String password) {
    super(username, email, phone, cpf, password, UserRole.ORGANIZADOR);
    this.caravanas = new HashSet<>();
  }
}

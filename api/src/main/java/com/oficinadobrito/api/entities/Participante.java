package com.oficinadobrito.api.entities;

import com.oficinadobrito.api.utils.enums.ParticipanteStatus;
import com.oficinadobrito.api.utils.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tb_participantes")
@PrimaryKeyJoinColumn(name = "usuarioId")
public class Participante extends Usuario{

    @Enumerated(EnumType.STRING)
    private ParticipanteStatus status;
    
    @ManyToMany(mappedBy = "participantes")
    private Set<Caravana> caravanas;

    public Participante(){
        this.caravanas = new HashSet<>();
    }
    
    public Participante(String username, String email, String phone, String cpf, String password) {
      super(username, email, phone, cpf, password,UserRole.PARTICIPANTE);
      this.caravanas = new HashSet<>(); 
      this.status = ParticipanteStatus.PENDENTE;
  }
}

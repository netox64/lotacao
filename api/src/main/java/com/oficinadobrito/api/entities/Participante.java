package com.oficinadobrito.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tb_participantes")
public class Participante extends Usuario{

    @ManyToMany(mappedBy = "participantes")
    private Set<Caravana> caravanas;

    public Participante(){
        this.caravanas = new HashSet<>();
    }
}

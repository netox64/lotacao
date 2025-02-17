package com.oficinadobrito.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tb_organizador")
public class Organizador extends Usuario{

    @OneToMany(mappedBy = "organizador")
    private Set<Caravana> caravanas;

    public Organizador(){
        this.caravanas = new HashSet<>();
    }

}

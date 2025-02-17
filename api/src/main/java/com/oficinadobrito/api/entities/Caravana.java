package com.oficinadobrito.api.entities;

import com.oficinadobrito.api.utils.dtos.caravana.CreateCaravanaDto;
import com.oficinadobrito.api.utils.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tb_caravanas")
public class Caravana {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger caravanaId;

    private LocalDate dataSaida;

    private LocalDate dataRetorno;

    private int quantVagas;

    private String descricao;

    private double precoPorPessoa;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(mappedBy = "caravanas")
    private Set<Cidade> cidades;

    //private Cidade cidadeOrigem;[0]  private Cidade cidadeDestino;[1]

    @OneToMany(mappedBy = "caravana")
    private Set<Postagem> posts;

    @ManyToOne
    @JoinColumn(name="usuarioId")
    private Organizador organizador;

    @ManyToMany(mappedBy = "caravanas")
    private Set<Motorista> motoristas;

    @ManyToMany(mappedBy = "caravanas")
    private Set<Onibus> frota;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "caravana_participante", joinColumns = { @JoinColumn(name = "caravanaId") }, inverseJoinColumns = { @JoinColumn(name = "usuarioId") })
    private Set<Participante> participantes;

    public Caravana(){
        this.motoristas = new HashSet<>();
        this.participantes = new HashSet<>();
        this.frota = new HashSet<>();
        this.posts = new HashSet<>();
    }

    public static Caravana createEntityToDto(CreateCaravanaDto dto){
      Caravana caravana = new Caravana();
      caravana.setDataSaida(dto.dataSaida());
      caravana.setDataRetorno(dto.dataRetorno());
      caravana.setQuantVagas(dto.quantVagas());
      caravana.setDescricao(dto.descricao());
      caravana.setPrecoPorPessoa(dto.precoPorPessoa());
      caravana.setStatus(Status.EM_ANDAMENTO);
      return caravana;
  }
  
  public void addMotorista(Motorista motorista){
      this.motoristas.add(motorista);
  }
  public void addCidade(Cidade cidade){
      this.cidades.add(cidade);
  }


}

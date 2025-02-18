package com.oficinadobrito.api.services;

import com.oficinadobrito.api.entities.Motorista;
import com.oficinadobrito.api.entities.Organizador;
import com.oficinadobrito.api.entities.Participante;
import com.oficinadobrito.api.entities.Usuario;
import com.oficinadobrito.api.repositories.generics.GenericUsersRepository;
import com.oficinadobrito.api.services.generics.GenericUsersServiceImpl;
import com.oficinadobrito.api.services.tiposdeusuarios.MotoristasServiceImpl;
import com.oficinadobrito.api.services.tiposdeusuarios.OrganizadoresServiceImpl;
import com.oficinadobrito.api.services.tiposdeusuarios.ParticipantesServiceImpl;
import com.oficinadobrito.api.services.usuario.UsuariosService;
import com.oficinadobrito.api.utils.GeradorSenhaRandom;
import com.oficinadobrito.api.utils.dtos.motorista.UpdateForMotoristaDto;
import com.oficinadobrito.api.utils.dtos.organizador.UpdateForOrganizadorDto;
import com.oficinadobrito.api.utils.dtos.participante.UpdateForParticipanteDto;
import com.oficinadobrito.api.utils.enums.UserRole;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MocaDoAtendimentoImpl extends GenericUsersServiceImpl<Participante> {
  
  @PersistenceContext
  private EntityManager entityManager;

  private final UsuariosService usuariosService;
  private final ParticipantesServiceImpl participantesService;
  private final OrganizadoresServiceImpl organizadoresService;
  private final MotoristasServiceImpl motoristasService;
  private final EmailsService emailsService;
  
  public MocaDoAtendimentoImpl(GenericUsersRepository<Participante> repository, UsuariosService usuariosService,ParticipantesServiceImpl participantesService,OrganizadoresServiceImpl organizadoresService,MotoristasServiceImpl motoristasService,EmailsService emailsService) {
    super(repository);
    this.usuariosService = usuariosService;
    this.participantesService = participantesService;
    this.organizadoresService = organizadoresService;
    this.motoristasService = motoristasService;
    this.emailsService = emailsService;
  }
  
  @Transactional
  public Motorista updateForMotorista(String usuarioId, UpdateForMotoristaDto usuarioDto) {
    Usuario usuario = this.usuariosService.findUsuarioById(usuarioId);
    String passwordGenerated = GeradorSenhaRandom.gerarSenhaAleatoria();

    Motorista motorista = new Motorista(usuario.getUsername(), usuario.getEmail(), usuario.getPhone(), usuario.getCpf(), passwordGenerated);

    Motorista motoristaSalvo = this.motoristasService.save(motorista);
    entityManager.flush();

    if (usuario.getRole() == UserRole.PARTICIPANTE) {
      this.participantesService.delete(usuarioId);
      entityManager.flush();
    } else {
      this.organizadoresService.delete(usuarioId);
      entityManager.flush();
    }
    this.usuariosService.delete(usuarioId);
    entityManager.flush();

    this.emailsService.enviarEmail(usuario.getEmail(),"Permissões Alteradas", "Suas Permissões no site foram alteradas no sistema, em consequencia disso uma nova senha para seu usuario foi criada e para sua segurança você deverá alterar o mais breve possivel " + passwordGenerated);
    return motoristaSalvo;
  }
  
  @Transactional
  public Organizador updateForOrganizador(String usuarioId, UpdateForOrganizadorDto usuarioDto) {
    Usuario usuario = this.usuariosService.findUsuarioById(usuarioId);
    String passwordGenerated = GeradorSenhaRandom.gerarSenhaAleatoria();
    
    Organizador organizador = new Organizador(usuario.getUsername(), usuario.getEmail(), usuario.getPhone(), usuario.getCpf(), passwordGenerated);
    
    Organizador organizadorSalvo = this.organizadoresService.save(organizador);
    entityManager.flush();
    
    if (usuario.getRole() == UserRole.PARTICIPANTE) {
      this.participantesService.delete(usuarioId);
      entityManager.flush();
    } else {
      this.motoristasService.delete(usuarioId);
      entityManager.flush();
    }
    this.usuariosService.delete(usuarioId);
    entityManager.flush();

    this.emailsService.enviarEmail(usuario.getEmail(),"Permissões Alteradas", "Suas Permissões no site foram alteradas no sistema, em consequencia disso uma nova senha para seu usuario foi criada e para sua segurança você deverá alterar o mais breve possivel " + passwordGenerated);
    return organizadorSalvo;
  }

  @Transactional
  public Participante updateForParticipante(String usuarioId,  UpdateForParticipanteDto usuarioDto) {
    Usuario usuario = this.usuariosService.findUsuarioById(usuarioId);
    String passwordGenerated = GeradorSenhaRandom.gerarSenhaAleatoria();
  
    Participante participante =  new Participante(usuario.getUsername(), usuario.getEmail(), usuario.getPhone(), usuario.getCpf(), passwordGenerated);

    Participante participanteSalvo = this.participantesService.save(participante);
    entityManager.flush();

    if (usuario.getRole() == UserRole.MOTORISTA) {
      this.motoristasService.delete(usuarioId);
      entityManager.flush();
    } else {
      this.organizadoresService.delete(usuarioId);
      entityManager.flush();
    }
    this.usuariosService.delete(usuarioId);
    entityManager.flush();

    this.emailsService.enviarEmail(usuario.getEmail(),"Permissões Alteradas", "Suas Permissões no site foram alteradas no sistema, em consequencia disso uma nova senha para seu usuario foi criada e para sua segurança você deverá alterar o mais breve possivel " + passwordGenerated);
    return participanteSalvo;
  }
  
  public Motorista getMotoristaById(String motoristaId){
    return this.motoristasService.findById(motoristaId);
  }

  public Participante getParticipanteById(String participanteId){
    return this.participantesService.findById(participanteId);
  }

  public Organizador getOrganizadorById(String organizadorId){
    return this.organizadoresService.findById(organizadorId);
  }
  
}

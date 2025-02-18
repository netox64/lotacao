package com.oficinadobrito.api.controllers;

import com.oficinadobrito.api.entities.Usuario;
import com.oficinadobrito.api.services.MocaDoAtendimentoImpl;
import com.oficinadobrito.api.services.usuario.UsuariosService;
import com.oficinadobrito.api.utils.dtos.motorista.UpdateForMotoristaDto;
import com.oficinadobrito.api.utils.dtos.organizador.UpdateForOrganizadorDto;
import com.oficinadobrito.api.utils.dtos.participante.UpdateForParticipanteDto;
import com.oficinadobrito.api.utils.dtos.usuario.UpdateUsuarioDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuariosControllerImpl { 
  
  private final UsuariosService usuariosService;
  private final MocaDoAtendimentoImpl mocaDoAtendimento;
  
  public UsuariosControllerImpl(UsuariosService usuariosService,MocaDoAtendimentoImpl mocaDoAtendimento){
    this.usuariosService = usuariosService;
    this.mocaDoAtendimento = mocaDoAtendimento;
  }

  @PreAuthorize("permitAll()")
  @PutMapping("/{id}")
  public ResponseEntity<Usuario> update(@PathVariable("id") String usuarioId, @RequestBody @Valid UpdateUsuarioDto usuarioDto){
    Usuario usuario = this.usuariosService.updateUsuario(usuarioId,usuarioDto);
    return ResponseEntity.ok(usuario);
  }

  @PreAuthorize("permitAll()")
  @PutMapping("/{id}/promote-to-motorista")
  public ResponseEntity<Usuario> updateForMotorista(@PathVariable("id") String usuarioId, @RequestBody UpdateForMotoristaDto usuarioDto){
    Usuario usuario = this.mocaDoAtendimento.updateForMotorista(usuarioId,usuarioDto);
    return ResponseEntity.ok(usuario);
  }

  @PreAuthorize("permitAll()")
  @PutMapping("/{id}/promote-to-organizador")
  public ResponseEntity<Usuario> updateForOrganizador(@PathVariable("id") String usuarioId, @RequestBody UpdateForOrganizadorDto usuarioDto){
    Usuario usuario = this.mocaDoAtendimento.updateForOrganizador(usuarioId,usuarioDto);
    return ResponseEntity.ok(usuario);
  }

  @PreAuthorize("permitAll()")
  @PutMapping("/{id}/demote-to-participante")
  public ResponseEntity<Usuario> updateForParticipante(@PathVariable("id") String usuarioId, @RequestBody UpdateForParticipanteDto usuarioDto){
    Usuario usuario = this.mocaDoAtendimento.updateForParticipante(usuarioId,usuarioDto);
    return ResponseEntity.ok(usuario);
  }
  
}

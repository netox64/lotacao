package com.oficinadobrito.api.entities;

import com.oficinadobrito.api.configs.listeners.ListenerUsuarios;
import com.oficinadobrito.api.utils.dtos.usuario.CreateUsuarioDto;
import com.oficinadobrito.api.utils.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EntityListeners(ListenerUsuarios.class)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tb_usuarios")
public class Usuario implements UserDetails {
    @Id
    @UuidGenerator
    private String usuarioId;
    private String username;
    private String email;
    private String image;
    private String phone;
    private String cpf;
    private String password;
    private UserRole role;
    private LocalDate dataCriacao;
    @OneToMany(mappedBy = "usuario")
    private Set<Postagem> postagens;

    public Usuario(){
        this.postagens = new HashSet<>();
    }
    public Usuario(String username, String email, String phone, String cpf, String password, UserRole role) {
        this();
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.cpf = cpf;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ADMIN"),new SimpleGrantedAuthority("USER"));
        else
            return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public static Usuario toEntity(CreateUsuarioDto dto) {
        return new Usuario(dto.username(), dto.email(), dto.phone(), dto.cpf(), dto.password(), UserRole.USER);
    }

}

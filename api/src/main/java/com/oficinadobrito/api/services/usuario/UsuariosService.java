package com.oficinadobrito.api.services.usuario;

import com.oficinadobrito.api.configs.exeptions.ResourceNotFoundException;
import com.oficinadobrito.api.entities.Usuario;
import com.oficinadobrito.api.repositories.UsuarioRepository;
import com.oficinadobrito.api.services.EmailsService;
import com.oficinadobrito.api.utils.VerifyTokenPassword;
import com.oficinadobrito.api.utils.dtos.usuario.LoginDto;
import com.oficinadobrito.api.utils.dtos.usuario.UpdateUsuarioDto;
import com.oficinadobrito.api.utils.validators.EmailValidator;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {

    private final JwtService jwtService;
    private final TokenEmailService tokenEmailService;
    private final AuthenticationManager authenticationManager;
    private final EmailsService emailsService;
    private final CpfEncryptService cpfEncryptService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuariosRepository;

    public UsuariosService(JwtService jwtService, AuthenticationManager authenticationManager, UsuarioRepository usuariosRepository, TokenEmailService tokenEmailService, EmailsService emailsService, CpfEncryptService cpfEncryptService,PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.usuariosRepository = usuariosRepository;
        this.tokenEmailService = tokenEmailService;
        this.emailsService = emailsService;
        this.cpfEncryptService = cpfEncryptService;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario create(Usuario user) throws BadRequestException {
        Optional<UserDetails> usuario = this.usuariosRepository.findByEmail(user.getEmail());
        if (!EmailValidator.isValidEmail(user.getEmail()) || usuario.isPresent()) {
            throw new BadRequestException("The email provided is in the wrong format or a user with that email is already in this application");
        }
        return this.usuariosRepository.save(user);
    }

    public String authenticarUsuario(LoginDto usuario) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(usuario.email(), usuario.password());
        try {
            Authentication authentication = this.authenticationManager.authenticate(usernamePassword);
            return jwtService.generateToken((Usuario) authentication.getPrincipal());
        } catch (AuthenticationException e) {
            return "";
        }
    }

    public boolean sendHash(String email) throws BadRequestException {
        Optional<Usuario> user = this.usuariosRepository.findUsuarioByEmail(email);
        if (user.isPresent()) {
            String token = null;
            try {
                token = this.tokenEmailService.encrypt(email);
            } catch (Exception e) {
                throw new BadRequestException("An error occurred while sending your code" + e.getMessage());
            }
            this.emailsService.enviarEmail(email, "Use o código para redefinir sua senha na aplicação " + "Divulga Potiguar", token);
            return true;
        }
        return false;
    }

    public VerifyTokenPassword verifyHash(String token) throws BadRequestException {
        try {
            String emailAndKey = this.tokenEmailService.decrypt(token);
            String[] separador = emailAndKey.split(":");
            if (this.tokenEmailService.validateToken(token, separador[0])) {
                return new VerifyTokenPassword(separador[0], true);
            }
        } catch (Exception e) {
            throw new BadRequestException("The token does not have the desired shape" + e.getMessage());
        }
        return new VerifyTokenPassword("", false);
    }

    public boolean redefinirSenha(String email, String novaSenha) {
        Optional<Usuario> user = this.usuariosRepository.findUsuarioByEmail(email);
        if (user.isPresent()) {
            Usuario atualizado = user.get();
            atualizado.setPassword(novaSenha);
            this.usuariosRepository.save(atualizado);
            return true;
        }
        return false;
    }

    public List<Usuario> getAll() {
        return this.usuariosRepository.findAll();
    }

    public Usuario findUsuarioById(String id) {
        return this.usuariosRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario with this id not exists"));
    }

    public Usuario findUsuarioByEmail(String email) {
        return this.usuariosRepository.findUsuarioByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario with this email not exists"));
    }

    public String searchCpf(String id) {
        Usuario usuario = this.findUsuarioById(id);
        return this.cpfEncryptService.decode(usuario.getCpf());
    }

    public Usuario save(Usuario user){
        return this.usuariosRepository.save(user);
    }

    public boolean usuarioThisUsernameExists(String username){
        Optional<Usuario> usuarioExists = this.usuariosRepository.findByUsername(username);
        return (usuarioExists.isPresent());
    }

    public Usuario updateUsuario(String id, UpdateUsuarioDto usuarioUpdate) throws BadRequestException {
        Usuario usuarioExistente = this.findUsuarioById(id);
        if (usuarioUpdate.password() != null) {
            if(!this.passwordEncoder.matches(usuarioUpdate.password(),usuarioExistente.getPassword())){
                throw new BadRequestException("user password is incorrect");
            }
            usuarioExistente.setPassword(usuarioUpdate.password());
        }
        if (usuarioUpdate.username() != null) {
            boolean userExists = this.usuarioThisUsernameExists(usuarioUpdate.username());
            boolean usuarioDiferenteDoLogado = !(usuarioExistente.getUsername().equalsIgnoreCase(usuarioUpdate.username()));
            if (userExists && usuarioDiferenteDoLogado ){
                throw new BadRequestException("There is already a user with this username in the application");
            }
            usuarioExistente.setUsername(usuarioUpdate.username());
        }
        if (usuarioUpdate.image() != null) {
            usuarioExistente.setImage(usuarioUpdate.image());
        }
        if (usuarioUpdate.phone() != null) {
            usuarioExistente.setPhone(usuarioUpdate.phone());
        }
        if (usuarioUpdate.role() != null) {
            usuarioExistente.setRole(usuarioUpdate.role());
            this.atualizarAuthoritiesDoUsuarioAutenticado(usuarioExistente);
        }
        return this.save(usuarioExistente);
    }

    private void atualizarAuthoritiesDoUsuarioAutenticado(Usuario usuario) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Usuario) {
            UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuth);
        }
    }
}

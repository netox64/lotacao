package com.oficinadobrito.api.configs.filters;

import com.oficinadobrito.api.repositories.UsuarioRepository;
import com.oficinadobrito.api.services.usuario.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
  private final UsuarioRepository userRepository;
  private final JwtService jwtService;

  public SecurityFilter(UsuarioRepository userRepository, JwtService jwtService) {
    this.userRepository = userRepository;
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
    var token = this.recoverToken(request);

    if(token != null){
      var subject = this.jwtService.validateToken(token);
      Optional<UserDetails> user = userRepository.findByEmail(subject);
      UserDetails usuario = null;
      if(user.isPresent()){
        usuario = user.get();
        var authentication = new UsernamePasswordAuthenticationToken( user,null,usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }else{
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário not found");
        return;
      }
    }
    filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request){
    var authHeader = request.getHeader("Authorization");
    if(authHeader == null) return null;
    return authHeader.replace("Bearer ","");

  }
}

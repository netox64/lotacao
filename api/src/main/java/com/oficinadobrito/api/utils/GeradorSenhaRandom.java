package com.oficinadobrito.api.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeradorSenhaRandom {
  private static final String LETRAS_MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
  private static final String LETRAS_MAIUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String DIGITOS = "0123456789";
  private static final String SIMBOLOS = "#*";
  private static final String TODOS_CARACTERES = LETRAS_MINUSCULAS + LETRAS_MAIUSCULAS + DIGITOS + SIMBOLOS;
  private static final int TAMANHO_MINIMO = 9;

  public static String gerarSenhaAleatoria() {
    SecureRandom random = new SecureRandom();
    List<Character> senha = new ArrayList<>();
    
    senha.add(LETRAS_MAIUSCULAS.charAt(random.nextInt(LETRAS_MAIUSCULAS.length()))); // Pelo menos 1 maiúscula
    senha.add(DIGITOS.charAt(random.nextInt(DIGITOS.length()))); // Primeiro número
    senha.add(DIGITOS.charAt(random.nextInt(DIGITOS.length()))); // Segundo número
    senha.add(SIMBOLOS.charAt(random.nextInt(SIMBOLOS.length()))); // Pelo menos 1 símbolo
    
    for (int i = 0; i < 3; i++) {
      senha.add(LETRAS_MINUSCULAS.charAt(random.nextInt(LETRAS_MINUSCULAS.length())));
    }
    
    while (senha.size() < TAMANHO_MINIMO) {
      senha.add(TODOS_CARACTERES.charAt(random.nextInt(TODOS_CARACTERES.length())));
    }
    Collections.shuffle(senha, random);
    
    StringBuilder senhaFinal = new StringBuilder();
    for (char c : senha) {
      senhaFinal.append(c);
    }

    return senhaFinal.toString();
  }
}

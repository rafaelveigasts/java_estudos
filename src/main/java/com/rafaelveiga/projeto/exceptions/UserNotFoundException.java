package com.rafaelveiga.projeto.exceptions;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    super("Usuário não existe");
  }
}
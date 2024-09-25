package com.rafaelveiga.projeto.exceptions;

public class JobNotFoundException extends RuntimeException {
  public JobNotFoundException() {
    super("Usuário não existe");
  }
}
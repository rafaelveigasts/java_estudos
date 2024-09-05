package com.rafaelveiga.projeto.modules.company.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelveiga.projeto.modules.company.dto.AuthCompanyDTO;
import com.rafaelveiga.projeto.modules.company.useCases.AuthCompanyUseCase;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

  @Autowired
  private AuthCompanyUseCase authCompanyUseCase;

  @PostMapping("companies/")
  public ResponseEntity auth(@RequestBody AuthCompanyDTO entity) throws AuthenticationException {
    try {
      return ResponseEntity.ok(authCompanyUseCase.execute(entity));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

  }

}

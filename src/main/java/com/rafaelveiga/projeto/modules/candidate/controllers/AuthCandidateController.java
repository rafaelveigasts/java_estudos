package com.rafaelveiga.projeto.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelveiga.projeto.modules.candidate.dto.AuthRequestCandidateDTO;
import com.rafaelveiga.projeto.modules.candidate.useCases.AuthCandidateUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth/candidate")
public class AuthCandidateController {

  @Autowired
  private AuthCandidateUseCase authCandidateUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> auth(@RequestBody AuthRequestCandidateDTO request) {

    try {
      var response = this.authCandidateUseCase.execute(request);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.UNAUTHORIZED)
          .body(e.getMessage());
    }

  }

}

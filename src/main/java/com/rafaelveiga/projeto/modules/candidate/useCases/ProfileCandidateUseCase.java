package com.rafaelveiga.projeto.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rafaelveiga.projeto.modules.candidate.CandidateEntity;
import com.rafaelveiga.projeto.modules.candidate.CandidateRepository.CandidateRepository;
import com.rafaelveiga.projeto.modules.candidate.dto.CandidateProfileResponseDTO;

@Service
public class ProfileCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public CandidateProfileResponseDTO execute(UUID id) {
    var candidate = this.candidateRepository.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

    return CandidateProfileResponseDTO.builder()
        .description(candidate.getDescription())
        .email(candidate.getEmail())
        .id(candidate.getId().toString())
        .name(candidate.getName())
        .username(candidate.getUsername())
        .build();
  }

}

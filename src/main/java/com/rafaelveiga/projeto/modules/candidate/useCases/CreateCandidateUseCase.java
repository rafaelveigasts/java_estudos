package com.rafaelveiga.projeto.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.rafaelveiga.projeto.exceptions.UserFoundException;
import com.rafaelveiga.projeto.modules.candidate.CandidateEntity;
import com.rafaelveiga.projeto.modules.candidate.CandidateRepository.CandidateRepository;

@Service
public class CreateCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public CandidateEntity execute(CandidateEntity candidateEntity) {

    this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(),
        candidateEntity.getEmail()).ifPresent(user -> {
          throw new UserFoundException();
        });

    candidateEntity.setPassword(passwordEncoder.encode(candidateEntity.getPassword()));

    return candidateRepository.save(candidateEntity);

  }

}

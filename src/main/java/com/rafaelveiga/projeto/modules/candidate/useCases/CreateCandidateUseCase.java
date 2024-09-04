package com.rafaelveiga.projeto.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rafaelveiga.projeto.exceptions.UserFoundException;
import com.rafaelveiga.projeto.modules.candidate.CandidateEntity;
import com.rafaelveiga.projeto.modules.candidate.CandidateRepository.CandidateRepository;

@Service
public class CreateCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public CandidateEntity execute(CandidateEntity candidateEntity) {

    this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(),
        candidateEntity.getEmail()).ifPresent(user -> {
          throw new UserFoundException();
        });

    return candidateRepository.save(candidateEntity);

  }

}

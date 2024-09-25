package com.rafaelveiga.projeto.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelveiga.projeto.exceptions.JobNotFoundException;
import com.rafaelveiga.projeto.exceptions.UserNotFoundException;
import com.rafaelveiga.projeto.modules.candidate.CandidateRepository.CandidateRepository;
import com.rafaelveiga.projeto.modules.candidate.entity.ApplyJobEntity;
import com.rafaelveiga.projeto.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private JobRepository jobRepository;

  public ApplyJobEntity execute(UUID candidateId, UUID jobId) {

    this.candidateRepository.findById(candidateId).orElseThrow(() -> new UserNotFoundException());

    this.jobRepository.findById(jobId).orElseThrow(() -> new JobNotFoundException());

    var applyJob = ApplyJobEntity.builder().candidateId(candidateId).jobId(jobId).build();

    return applyJob;
  }

}

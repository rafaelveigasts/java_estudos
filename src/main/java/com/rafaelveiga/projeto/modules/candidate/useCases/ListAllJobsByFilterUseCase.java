package com.rafaelveiga.projeto.modules.candidate.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelveiga.projeto.modules.company.entities.JobEntity;
import com.rafaelveiga.projeto.modules.company.repositories.JobRepository;

@Service
public class ListAllJobsByFilterUseCase {
  @Autowired
  private JobRepository jobRepository;

  public List<JobEntity> execute(String filter) {
    return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);

  }

}

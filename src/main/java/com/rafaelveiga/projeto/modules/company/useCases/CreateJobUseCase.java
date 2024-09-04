package com.rafaelveiga.projeto.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelveiga.projeto.modules.company.entities.JobEntity;
import com.rafaelveiga.projeto.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {

  @Autowired
  private JobRepository jobRepository;

  public JobEntity execute(JobEntity job) {

    return this.jobRepository.save(job);

  }

}

package com.rafaelveiga.projeto.modules.candidate.useCases;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rafaelveiga.projeto.exceptions.JobNotFoundException;
import com.rafaelveiga.projeto.exceptions.UserNotFoundException;
import com.rafaelveiga.projeto.modules.candidate.CandidateEntity;
import com.rafaelveiga.projeto.modules.candidate.CandidateRepository.ApplyJobRepository;
import com.rafaelveiga.projeto.modules.candidate.CandidateRepository.CandidateRepository;
import com.rafaelveiga.projeto.modules.candidate.entity.ApplyJobEntity;
import com.rafaelveiga.projeto.modules.company.entities.JobEntity;
import com.rafaelveiga.projeto.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

  @InjectMocks
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @Mock // repository we use mock to simulate the behavior of the repository and not
        // persist data in the database. Use case we use injectMocks to test the
        // behavior of the use case.
  private CandidateRepository candidateRepository;

  @Mock
  private JobRepository jobRepository;

  @Mock
  private ApplyJobRepository applyJobRepository;

  @Test
  @DisplayName("should be able to apply for a job with a valid candidate")
  public void should_not_be_able_to_apply_for_a_job_with_an_invalid_candidate() {
    try {
      applyJobCandidateUseCase.execute(null, null);
    } catch (Exception e) {
      assertThrows(UserNotFoundException.class, () -> {
        throw e;
      });
    }

  }

  @Test
  @DisplayName("should not be able to apply for a job with an invalid job")
  public void should_not_be_able_to_apply_for_a_job_with_an_invalid_job() {

    var idCandidate = UUID.randomUUID();
    var candidate = new CandidateEntity();

    candidate.setId(idCandidate);
    when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

    try {
      applyJobCandidateUseCase.execute(idCandidate, null);
    } catch (Exception e) {
      assertThrows(JobNotFoundException.class, () -> {
        throw e;
      });
    }

  }

  @Test
  @DisplayName("should create a new ApplyJobEntity when candidate and job are valid")
  public void should_create_a_new_ApplyJobEntity_when_candidate_and_job_are_valid() {
    UUID candidateId = UUID.randomUUID();
    UUID jobId = UUID.randomUUID();

    when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));
    when(jobRepository.findById(jobId)).thenReturn(Optional.of(new JobEntity()));

    ApplyJobEntity result = applyJobCandidateUseCase.execute(candidateId, jobId);

    assertNotNull(result);
    assertNotNull(result.getCandidateId());
    assertNotNull(result.getJobId());
  }

}

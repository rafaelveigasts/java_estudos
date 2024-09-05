package com.rafaelveiga.projeto.modules.candidate.CandidateRepository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rafaelveiga.projeto.modules.candidate.CandidateEntity;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
  Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);

  Optional<CandidateEntity> findByUsername(String username);

  Optional<CandidateEntity> findById(UUID id);
}

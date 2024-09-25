package com.rafaelveiga.projeto.modules.candidate.CandidateRepository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelveiga.projeto.modules.candidate.entity.ApplyJobEntity;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {

}

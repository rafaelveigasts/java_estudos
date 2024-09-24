package com.rafaelveiga.projeto.modules.candidate.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelveiga.projeto.modules.candidate.CandidateEntity;
import com.rafaelveiga.projeto.modules.candidate.dto.CandidateProfileResponseDTO;
import com.rafaelveiga.projeto.modules.candidate.useCases.CreateCandidateUseCase;
import com.rafaelveiga.projeto.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import com.rafaelveiga.projeto.modules.candidate.useCases.ProfileCandidateUseCase;
import com.rafaelveiga.projeto.modules.company.entities.JobEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/candidates")
@Tag(name = "Candidate")

public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;

  @Autowired
  private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

  @PostMapping("/")
  @Operation(summary = "Cadastro de candidato", description = "Essa função é responsável por cadastrar um candidato")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = CandidateEntity.class))
      }),
      @ApiResponse(responseCode = "400", description = "Usuário já existe")
  })
  public ResponseEntity<Object> createCandidate(@Valid @RequestBody CandidateEntity candidateEntity) {

    try {
      var result = createCandidateUseCase.execute(candidateEntity);
      return ResponseEntity.ok(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

  @GetMapping("/")
  @PreAuthorize("hasRole('ROLE_CANDIDATE')")
  @Operation(summary = "Perfil do candidato", description = "Essa função é responsável por retornar o perfil do candidato")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(array = @ArraySchema(schema = @Schema(implementation = CandidateProfileResponseDTO.class)))
      }),
      @ApiResponse(responseCode = "400", description = "User not found")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> getProfileData(HttpServletRequest request) {

    var candidateId = request.getParameter("idCandidate");

    try {
      var profile = this.profileCandidateUseCase.execute(UUID.fromString((String) candidateId));
      return ResponseEntity.ok(profile);

    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/jobs")
  @PreAuthorize("hasRole('ROLE_CANDIDATE')")
  @Operation(summary = "Perfil do candidato", description = "Essa função é responsável por retornar o perfil do candidato")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
      })
  })
  @SecurityRequirement(name = "jwt_auth")
  public List<JobEntity> findJobByFilter(@RequestParam String filter) {
    return this.listAllJobsByFilterUseCase.execute(filter);
  }

}
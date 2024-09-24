package com.rafaelveiga.projeto.modules.candidate.dto;

import org.hibernate.validator.constraints.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CandidateProfileResponseDTO {
  @Schema(example = "Desenvolvedora Java")
  private String description;
  @Schema(example = "maria")
  private String username;
  @Schema(example = "maria@gmail.com")
  private String email;
  private UUID id;
  @Schema(example = "Maria de Souza")
  private String name;

}

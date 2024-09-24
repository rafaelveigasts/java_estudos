package com.rafaelveiga.projeto.modules.candidate.dto;

import java.util.UUID;

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

  @Schema(example = "123e4567-e89b-12d3-a456-426614174000")
  private UUID id;

  @Schema(example = "Maria de Souza")
  private String name;

}

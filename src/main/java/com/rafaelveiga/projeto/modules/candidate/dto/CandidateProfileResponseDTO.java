package com.rafaelveiga.projeto.modules.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CandidateProfileResponseDTO {
  private String description;
  private String username;
  private String email;
  private String id;
  private String name;

}

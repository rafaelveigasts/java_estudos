package com.rafaelveiga.projeto.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateJobDTO {
  @Schema(example = "GymPass, Plano de saúde", required = true)
  private String benefits;
  @Schema(example = "Vaga para pessoa desenvolvedora Java", required = true)
  private String description;
  @Schema(example = "Júnior", required = true)
  private String level;

}

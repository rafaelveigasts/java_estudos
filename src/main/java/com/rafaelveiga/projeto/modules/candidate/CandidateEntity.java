package com.rafaelveiga.projeto.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidates")
public class CandidateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Schema(example = "Daniel de Souza", requiredMode = RequiredMode.REQUIRED, description = "Nome do candidato")
  private String name;

  @Pattern(regexp = "\\S+", message = "[Username] inválido")
  @Schema(example = "daniel", requiredMode = RequiredMode.REQUIRED, description = "Username do candidato")
  private String username;

  @Email(message = "[Email] inválido")
  @Schema(example = "daniel@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "E-mail do candidato")
  private String email;

  @Schema(example = "admin@1234", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED, description = "Senha do candidato")
  private String password;

  @Schema(example = "Desenvolvedor Java", requiredMode = RequiredMode.REQUIRED, description = "Breve descrição do candidato")
  private String description;
  private String curriculum;

  private LocalDateTime createdAt = LocalDateTime.now();

}

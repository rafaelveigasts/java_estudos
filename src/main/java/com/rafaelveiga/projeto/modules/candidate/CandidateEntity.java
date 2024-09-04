package com.rafaelveiga.projeto.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

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
  private String name;

  @Pattern(regexp = "\\S+", message = "[Username] inválido")
  private String username;

  @Email(message = "[Email] inválido")
  private String email;
  private String password;
  private String description;
  private String curriculum;

  private LocalDateTime createdAt = LocalDateTime.now();

}

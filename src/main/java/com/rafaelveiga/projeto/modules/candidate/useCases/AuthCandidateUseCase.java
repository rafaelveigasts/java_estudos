package com.rafaelveiga.projeto.modules.candidate.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.rafaelveiga.projeto.modules.candidate.CandidateRepository.CandidateRepository;
import com.rafaelveiga.projeto.modules.candidate.dto.AuthCandidateResponseDTO;
import com.rafaelveiga.projeto.modules.candidate.dto.AuthRequestCandidateDTO;

@Service
public class AuthCandidateUseCase {
  @Value("${security.token.secret.candidate}")
  private String secret;

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthCandidateResponseDTO execute(AuthRequestCandidateDTO authRequestCandidateDTO) {

    var exists = this.candidateRepository.findByUsername(authRequestCandidateDTO.username()).orElseThrow(() -> {
      throw new UsernameNotFoundException("User not found");
    });

    if (!passwordEncoder.matches(authRequestCandidateDTO.password(), exists.getPassword())) {
      throw new UsernameNotFoundException("Invalid password");
    }

    Algorithm algorithm = Algorithm.HMAC256(secret);

    var token = JWT.create()
        .withIssuer("candidate")
        .withSubject(exists.getId().toString())
        .withExpiresAt(Instant.now().plus(Duration.ofDays(1)))
        .withClaim("roles", Arrays.asList("CANDIDATE"))
        .sign(algorithm);

    return AuthCandidateResponseDTO.builder()
        .access_token(token)
        .build();

  }
}

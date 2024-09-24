package com.rafaelveiga.projeto.modules.company.useCases;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.rafaelveiga.projeto.modules.company.dto.AuthCompanyDTO;
import com.rafaelveiga.projeto.modules.company.dto.AuthCompanyResponseDTO;
import com.rafaelveiga.projeto.modules.company.repositories.CompanyRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {

  @Value("${security.token.secret}")
  private String secret;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {

    var exists = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(() -> {
      throw new UsernameNotFoundException("User not found");
    });

    var match = this.passwordEncoder.matches(authCompanyDTO.getPassword(), exists.getPassword());

    if (!match) {
      throw new AuthenticationException("Invalid password");
    }

    Algorithm algorithm = Algorithm.HMAC256(secret);

    var expiresIn = Instant.now().plus(Duration.ofDays(1));

    var token = JWT
        .create()
        .withIssuer("auth0")
        .withSubject(exists.getId().toString())
        .withClaim("roles", Arrays.asList("COMPANY"))
        .withExpiresAt(expiresIn)
        .sign(algorithm);

    return AuthCompanyResponseDTO.builder()
        .access_token(token)
        .expiresIn(expiresIn.getEpochSecond())
        .build();

  }
}

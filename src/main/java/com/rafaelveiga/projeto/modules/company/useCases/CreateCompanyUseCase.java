package com.rafaelveiga.projeto.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rafaelveiga.projeto.exceptions.UserFoundException;
import com.rafaelveiga.projeto.modules.company.entities.CompanyEntity;
import com.rafaelveiga.projeto.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
  @Autowired
  private CompanyRepository companyRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public CompanyEntity execute(CompanyEntity company) {

    this.companyRepository
        .findByUsernameOrEmail(company.getUsername(), company.getEmail())
        .ifPresent(
            (c) -> {
              throw new UserFoundException();
            });

    company.setPassword(this.passwordEncoder.encode(company.getPassword()));

    return this.companyRepository.save(company);
  }
}

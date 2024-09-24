package com.rafaelveiga.projeto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Gestão de vagas")
            .description("API para gestão de vagas de emprego")
            .version("v1"))
        // .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
        // .components(new Components().addSecuritySchemes("bearerAuth",
        // createSecurityScheme()));
        .schemaRequirement("jwt_auth", createSecurityScheme());
  }

  private SecurityScheme createSecurityScheme() {
    return new SecurityScheme()
        .name("jwt_auth")
        .type(SecurityScheme.Type.HTTP)
        .scheme("bearer")
        .bearerFormat("JWT");
  }

}

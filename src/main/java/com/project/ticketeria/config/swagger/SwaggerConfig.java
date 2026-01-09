package com.project.ticketeria.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Ticketeria Online")
                        .description("API responsável por ....")
                        .version("1.0"))
                .schemaRequirement("jwt_auth", creaSecurityScheme());

    }

    public SecurityScheme creaSecurityScheme() {
        return new SecurityScheme().name("jwt_auth").scheme("bearer").bearerFormat("JWT")
                .type(SecurityScheme.Type.HTTP);
    }
}

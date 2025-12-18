package com.project.ticketeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
// permitirá que o Swagger rode com a documentação/swagger
@OpenAPIDefinition(info = @Info(title = "Ticketeria Online", description = "API  responsável pela gestão da Ticketeria online", version = "1.0"))
public class TicketeriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketeriaApplication.class, args);
	}

}

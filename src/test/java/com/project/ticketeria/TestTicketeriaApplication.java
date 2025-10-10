package com.project.ticketeria;

import org.springframework.boot.SpringApplication;

public class TestTicketeriaApplication {

	public static void main(String[] args) {
		SpringApplication.from(TicketeriaApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

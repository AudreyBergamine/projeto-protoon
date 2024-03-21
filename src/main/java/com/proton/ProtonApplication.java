package com.proton;

import org.springframework.boot.ApplicationRunner;

// TODO:     *** ARQUIVO PARA RODAR A APLICAÇÃO ***

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProtonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProtonApplication.class, args);
		System.out.println("\nRodando...\n");
	}

	@Bean //Imprime o hash da senha criado pelo data.sql no inicio da aplicação
	ApplicationRunner runner(PasswordEncoder passwordEncoder) {
		return args -> System.out.println(passwordEncoder.encode("password"));
	}
}

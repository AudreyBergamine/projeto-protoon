package com.proton;

// TODO:     *** ARQUIVO PARA RODAR A APLICAÇÃO ***

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class ProtonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProtonApplication.class, args);
		System.out.println("\nRodando...\n");
	}

	@Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }
}

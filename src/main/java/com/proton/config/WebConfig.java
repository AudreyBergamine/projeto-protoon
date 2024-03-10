// CLASSE AUXILIAR DE CONFIGURAÇÃO ESPECÍFICA PARA O PERFIL DE TESTE PARA INSTANCIAR O BANCO DE DADOS

package com.proton.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permitir solicitações de todas as origens
                .allowedOrigins("http://localhost:3000") // Permitir solicitações apenas de http://localhost:3000
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir métodos específicos
                .allowedHeaders("*"); // Permitir todos os headers
    }
}
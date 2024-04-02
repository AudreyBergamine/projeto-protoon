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
                .allowedOrigins("http://localhost:3000", "https://proto-on.vercel.app/") // Permitir solicitações apenas de http://localhost:3000
                .allowedOrigins("http://localhost:3000", "https://thankful-coast-0bf9e7a0f.5.azurestaticapps.net") // Permitir solicitações apenas de http://localhost:3000
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir métodos específicos
                .allowedHeaders("*"); // Permitir todos os headers
    }
}
package com.ipn.mx.avance2proyecto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/*")
                // ✅ CAMBIO CLAVE: Usamos 'allowedOriginPatterns' con asterisco
                // Esto permite Localhost, Netlify, Render, tu celular... ¡Todo!
                // y sigue funcionando con .allowCredentials(true)
                .allowedOriginPatterns("") 
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
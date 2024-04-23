package com.two4h.two4h;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Two4HApplication {

    public static void main(String[] args) {
        SpringApplication.run(Two4HApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        String[] allowDomains = new String[2];
        allowDomains[0] = "http://localhost:4200/";
        System.out.println("CORS configuration....");
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowDomains)
                        .allowedMethods("GET","POST","PUT","DELETE");
            }
        };
    }
}


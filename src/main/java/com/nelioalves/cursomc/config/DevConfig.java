package com.nelioalves.cursomc.config;

import java.text.ParseException;

import com.nelioalves.cursomc.services.DBService;
import com.nelioalves.cursomc.services.EmailService;
import com.nelioalves.cursomc.services.SmtpEmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        
        if (!"create".equals(strategy) ) {
            return false;
        }
        
        dbService.instantiateTestDatabase();
        return true;
    }

    /**
     * 61. MockEmailService com Logger. Padroes Strategy e Template Method (aos 12 min.)
     * Usando a anotação @Bean, permite-nos a usar a injeção de dependência deste @Bean em outra classe.
     * Quando ela for referenciada, o Spring Boot checará isto.
     */
    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
package com.nelioalves.cursomc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Todo o conteúdo de criação da base de testes foi movido para a classe 
 * src\main\java\com\nelioalves\cursomc\services\DBService.java
 */
@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) {
		
	}

}

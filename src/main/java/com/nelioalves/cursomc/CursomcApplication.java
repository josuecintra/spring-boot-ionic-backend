package com.nelioalves.cursomc;

import com.nelioalves.cursomc.services.S3Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Todo o conteúdo de criação da base de testes foi movido para a classe 
 * src\main\java\com\nelioalves\cursomc\services\DBService.java
 */
@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) {
		s3Service.uploadFile("‪C:\\Temp\\thispersondoesnotexist-w1.jpg");
	}

}

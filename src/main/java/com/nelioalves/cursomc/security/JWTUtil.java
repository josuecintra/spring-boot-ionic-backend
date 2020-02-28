package com.nelioalves.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// Para que a classe seja injetada como componente
@Component
public class JWTUtil {
	// 69. Implementando autenticacao e geracao do token JWT (PARTE 1) ~21min. usando uma variável 
	// definida em application.properties
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(String username) {
		return Jwts.builder()
			.setSubject(username)
			.setExpiration(new Date(System.currentTimeMillis() + expiration))
			.signWith(SignatureAlgorithm.HS512, secret.getBytes())
			.compact();
	}
}

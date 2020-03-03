package com.nelioalves.cursomc.services;

import java.util.Random;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private EmailService em;

    private Random rand = new Random();

    public void sendNewPassword(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new ObjectNotFoundException("E-mail não encontrado");
        }

        String newPass = newPassword();
        cliente.setSenha(pe.encode(newPass));

        clienteRepository.save(cliente);
        em.sendNewPasswordEmail(cliente, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++) {
            vet[i] = randonChar();
        }

        return new String(vet);
    }

    private char randonChar() {
        int opt = rand.nextInt(3);
        // https://unicode-table.com/pt/
        if (opt == 0) { // gera um dígito
            return (char) (rand.nextInt(10) + 48);
        } else if (opt == 1) { // gera letra maiúscula
            return (char) (rand.nextInt(26) + 65);
        } else { // gera letra minúscula
            return (char) (rand.nextInt(26) + 97);
        }
    }
}

package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.security.UserSS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Registra a classe como um serviço, permitindo que ela seja injetada noutra classe via @Autowired
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
    @Autowired
    private ClienteRepository repo;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cli = repo.findByEmail(username);
        if (cli == null) {
            throw new UsernameNotFoundException(username);
        }

		return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}

}

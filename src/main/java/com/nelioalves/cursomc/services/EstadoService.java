package com.nelioalves.cursomc.services;

import java.util.List;

import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.repositories.EstadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repo;

    public List<Estado> findAllByOrderByName() {
        return repo.findAllByOrderByNome();
    }
}

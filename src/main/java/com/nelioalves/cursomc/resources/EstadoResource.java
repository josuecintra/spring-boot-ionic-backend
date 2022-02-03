package com.nelioalves.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.dto.EstadoDTO;
import com.nelioalves.cursomc.services.EstadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
    @Autowired
    private EstadoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<Estado> list = service.findAllByOrderByName();
        List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
}

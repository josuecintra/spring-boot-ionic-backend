package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.services.CategoriaService;

/**
 * "72. Autorizando endpoints para perfis específicos"
 * Foi aplicado, neste projeto, a regra de negócios que só o Admin. possa fazer POST, PUT e DELETE de 
 * CategoriasResouce(), ficando os demais resouces "a gosto do freguês", de acordo com sua regra de negócios.
 * 
 * A anotação @PreAuthorize("hasAnyRole('ADMIN')") DEVE vir antas das demais anotações
 * 
 * Ao final da aulta, fez mais algumas restrições no resource ClientesResouce(), restingindo as operações de 
 * acordo com o perfil do usr logado.
 * 
 * No PedidoResource(), as operações apenas exigem que o cliente esteja logado para listar seus pedidos e fazer
 * um novo pedido.
 * 
 * No ProdutosResource(), temos apenas endpoints GET e públicos.
 */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {

		Categoria obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto) {
		Categoria obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	// @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	// @PutMapping("/{id}"), by Carlos
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id) {
		Categoria obj =service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		
		// Convertendo uma lista do tipo Categoria para CategoriaDTO: manda a lista pro DTO e faz a conversão em um método próprio
		List <Categoria> list = service.findAll();
		List <CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}
	
	// defaultValue é uma forma de deixar estes parâmetros opcionais c/ @RequestParam()
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
		@RequestParam(value = "page", defaultValue = "0") Integer page, 
		@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
		@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
		@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		Page <Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page <CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));

		return ResponseEntity.ok().body(listDTO);
	}


}


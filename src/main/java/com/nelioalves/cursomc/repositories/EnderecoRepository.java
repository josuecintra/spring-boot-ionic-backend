package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    
    // Se você criou o projeto usando Spring Boot versão 2.x.x:
    // https://github.com/acenelio/springboot2-ionic-backend

    // No programa principal, onde na aula é mostrado:
    //      categoriaRepository.save(Arrays.asList(cat1, cat2));
    // Troque pelo seguinte código:
    //      categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

}

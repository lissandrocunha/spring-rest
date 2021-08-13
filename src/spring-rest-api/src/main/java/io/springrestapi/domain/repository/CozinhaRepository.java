package io.springrestapi.domain.repository;

import java.util.List;

import io.springrestapi.domain.model.Cozinha;

public interface CozinhaRepository {

	
	List<Cozinha> listar();

	Cozinha buscar(Long id);

	Cozinha salvar(Cozinha cozinha);

	void remover(Long id);
		
}

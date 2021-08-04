package io.springrestapi.domain.repository;

import java.util.List;

import io.springrestapi.domain.model.Cozinha;

public interface CozinhaRepository {

	
	List<Cozinha> todos();

	Cozinha porId(Long id);

	Cozinha adicionar(Cozinha cozinha);

	void remover(Cozinha cozinha);
		
}

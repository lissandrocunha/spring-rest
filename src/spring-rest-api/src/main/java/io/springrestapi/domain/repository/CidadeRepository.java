package io.springrestapi.domain.repository;

import java.util.List;

import io.springrestapi.domain.model.Cidade;

public interface CidadeRepository {

	List<Cidade> todos();

	Cidade porId(Long id);

	Cidade adicionar(Cidade cidade);

	void remover(Cidade cidade);

}

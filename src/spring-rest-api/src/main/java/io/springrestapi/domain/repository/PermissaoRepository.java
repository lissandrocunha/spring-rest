package io.springrestapi.domain.repository;

import java.util.List;

import io.springrestapi.domain.model.Permissao;

public interface PermissaoRepository {

	List<Permissao> todos();

	Permissao porId(Long id);

	Permissao adicionar(Permissao permissao);

	void remover(Permissao permissao);

}

package io.springrestapi.domain.repository;

import java.util.List;

import io.springrestapi.domain.model.Estado;

public interface EstadoRepository {

	List<Estado> todos();

	Estado porId(Long id);

	Estado adicionar(Estado estado);

	void remover(Estado estado);
}

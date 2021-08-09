package io.springrestapi.domain.repository;

import java.util.List;

import io.springrestapi.domain.model.Estado;

public interface EstadoRepository {

	List<Estado> listar();

	Estado buscar(Long id);

	Estado salvar(Estado estado);

	void remover(Estado estado);
}

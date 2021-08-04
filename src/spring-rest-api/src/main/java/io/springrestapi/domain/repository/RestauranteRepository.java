package io.springrestapi.domain.repository;

import java.util.List;

import io.springrestapi.domain.model.Cozinha;
import io.springrestapi.domain.model.Restaurante;

public interface RestauranteRepository {

	List<Restaurante> todos();

	Restaurante porId(Long id);

	Restaurante adicionar(Restaurante restaurante);

	void remover(Restaurante restaurante);

}

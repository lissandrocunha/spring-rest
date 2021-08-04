package io.springrestapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.springrestapi.domain.model.Cozinha;
import io.springrestapi.domain.model.Restaurante;
import io.springrestapi.domain.repository.RestauranteRepository;

public class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> todos() {
		return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

	@Override
	public Restaurante porId(Long id) {

		return manager.find(Restaurante.class, id);
	}

	@Override
	public Restaurante adicionar(Restaurante restaurante) {

		return manager.merge(restaurante);
	}

	@Override
	public void remover(Restaurante restaurante) {
		restaurante = porId(restaurante.getId());
		manager.remove(restaurante);

	}

}

package io.springrestapi.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import io.springrestapi.domain.model.Restaurante;
import io.springrestapi.domain.repository.RestaurantesRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestaurantesRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> consultar(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

		var jpql = "from Restaurante where nome like :nome " + "and taxaFrete between :taxaFreteInicial and :taxaFreteFinal";

		return manager.createQuery(jpql, Restaurante.class).setParameter("nome", "%" + nome + "%")
				.setParameter("taxaFreteInicial", taxaFreteInicial).setParameter("taxaFreteFinal", taxaFreteFinal)
				.getResultList();
	}

}

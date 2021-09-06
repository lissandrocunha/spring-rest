package io.springrestapi.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import io.springrestapi.domain.model.Restaurante;
import io.springrestapi.domain.repository.RestaurantesRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestaurantesRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> consultar(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteria.from(Restaurante.class);

		Predicate nomePredicate = builder.like(root.get("nome"), "%" + nome + "%");
		Predicate taxaIninical = builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial);
		Predicate taxaFinal = builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal);

		criteria.where(nomePredicate,taxaIninical, taxaFinal);

		TypedQuery<Restaurante> query = manager.createQuery(criteria);

		return query.getResultList();
	}

}

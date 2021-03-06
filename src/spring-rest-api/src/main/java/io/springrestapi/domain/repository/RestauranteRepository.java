package io.springrestapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.springrestapi.domain.model.Restaurante;

@Repository
public interface RestauranteRepository
		extends CustomJpaRepository<Restaurante, Long>, JpaSpecificationExecutor<Restaurante>, RestaurantesRepositoryQueries {

	List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

//	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);

//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

	Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

	List<Restaurante> findTop2ByNomeContaining(String nome);

	int countByCozinhaId(Long cozinha);

}

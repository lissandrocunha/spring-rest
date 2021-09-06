package io.springrestapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.springrestapi.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

	List<Cozinha> findCozinhasByNomeContaining(String nome);

	Optional<Cozinha> findByNome(String nome);

	boolean existsByNome(String nome);
	
}

package io.springrestapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import io.springrestapi.domain.model.Restaurante;

public interface RestaurantesRepositoryQueries {

	List<Restaurante> consultar(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	List<Restaurante> findComFreteGratis(String nome);

}
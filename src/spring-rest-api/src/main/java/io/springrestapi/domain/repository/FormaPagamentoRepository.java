package io.springrestapi.domain.repository;

import java.util.List;

import io.springrestapi.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

	List<FormaPagamento> todos();

	FormaPagamento porId(Long id);

	FormaPagamento adicionar(FormaPagamento formaPagamento);

	void remover(FormaPagamento formaPagamento);

}

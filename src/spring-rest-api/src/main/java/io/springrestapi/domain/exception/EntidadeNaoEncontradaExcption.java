package io.springrestapi.domain.exception;

public class EntidadeNaoEncontradaExcption extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaExcption(String mensagem) {
		super(mensagem);
	}

}

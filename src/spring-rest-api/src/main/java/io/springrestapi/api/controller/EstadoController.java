package io.springrestapi.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.springrestapi.domain.exception.EntidadeEmUsoException;
import io.springrestapi.domain.exception.EntidadeNaoEncontradaException;
import io.springrestapi.domain.model.Estado;
import io.springrestapi.domain.repository.EstadoRepository;
import io.springrestapi.domain.service.CadastroEstadoService;

@Controller
@RequestMapping(value = "/estados")
@RestController
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstado;

	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.listar();
	}

	@GetMapping(value = "/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
		Estado estado = estadoRepository.buscar(estadoId);

		if (estado == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(estado);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado estado) {
		return cadastroEstado.salvar(estado);
	}

	@PutMapping("/{estadoId}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
		Estado estadoAtual = estadoRepository.buscar(estadoId);

		if (estadoAtual == null) {
			ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(estado, estadoAtual, "id");
		estadoAtual = cadastroEstado.salvar(estadoAtual);

		return ResponseEntity.ok(estadoAtual);
	}

	@DeleteMapping(value = "/{estadoId}")
	public ResponseEntity<?> remover(@PathVariable Long estadoId) {
		try {
			cadastroEstado.remover(estadoId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}

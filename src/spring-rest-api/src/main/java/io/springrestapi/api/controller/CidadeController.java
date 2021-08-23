package io.springrestapi.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.springrestapi.domain.exception.EntidadeNaoEncontradaException;
import io.springrestapi.domain.model.Cidade;
import io.springrestapi.domain.repository.CidadeRepository;
import io.springrestapi.domain.service.CadastroCidadeService;

@Controller
@RequestMapping(value = "/cidades")
@RestController
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	@GetMapping(value = "/{cidadeId}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
		Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);

		if (cidade.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(cidade.get());
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody Cidade cidade) {
		return cadastroCidade.salvar(cidade);
	}

	@PutMapping(value = "/{cidadeId}")
	public ResponseEntity<?> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
		try {
			Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);

			if (cidadeAtual.isEmpty()) {
				return ResponseEntity.notFound().build();
			}

			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			Cidade cidadeSalva = cadastroCidade.salvar(cidadeAtual.get());

			return ResponseEntity.ok(cidadeSalva);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}

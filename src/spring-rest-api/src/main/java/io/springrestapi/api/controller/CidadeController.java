package io.springrestapi.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
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
		return cidadeRepository.listar();
	}

	@GetMapping(value = "/{cidadeId}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
		Cidade cidade = cidadeRepository.buscar(cidadeId);

		if (cidade == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(cidade);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody Cidade cidade) {
		return cadastroCidade.salvar(cidade);
	}

	@PutMapping(value = "/{cidadeId}")
	public ResponseEntity<?> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
		try {
			Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);

			if (cidadeAtual == null) {
				return ResponseEntity.notFound().build();
			}

			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			cidadeAtual = cadastroCidade.salvar(cidadeAtual);

			return ResponseEntity.ok(cidadeAtual);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}

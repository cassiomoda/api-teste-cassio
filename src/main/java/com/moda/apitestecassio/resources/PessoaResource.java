package com.moda.apitestecassio.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moda.apitestecassio.dto.PessoaDto;
import com.moda.apitestecassio.dto.RespostaApiDto;
import com.moda.apitestecassio.dto.RespostaProcessamentoDoadoresDto;
import com.moda.apitestecassio.entities.Pessoa;
import com.moda.apitestecassio.exceptions.ApiException;
import com.moda.apitestecassio.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService service;

	@GetMapping
	public ResponseEntity<List<Pessoa>> findAll() throws ApiException {

		try {

			List<Pessoa> listaPessoas = service.findAll();
			return ResponseEntity.ok().body(listaPessoas);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException("Erro ao listar pessoas.", HttpStatus.BAD_GATEWAY.value());
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable Long id) throws ApiException {

		try {

			Pessoa pessoa = service.findById(id);
			return ResponseEntity.ok().body(pessoa);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException("Erro tentar localizar pessoa.", HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	@GetMapping(value = "/processar-doadores-gravados")
	public ResponseEntity<RespostaProcessamentoDoadoresDto> processarDoadoresGravados() throws ApiException {

		try {
			return ResponseEntity.ok().body(service.processarDoadoresGravados());

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException("Erro ao processar lista de doadores.", HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	@PostMapping
	public ResponseEntity<RespostaApiDto> cadastrarPessoa(@RequestBody PessoaDto pessoaDto) throws ApiException {

		try {

			RespostaApiDto respostaDto = new RespostaApiDto();
			respostaDto.adicionarPessoa(service.cadastrarPessoa(pessoaDto));
			respostaDto.setMsg("Pessoa cadastrada com sucesso!!!");
			respostaDto.setStatus(HttpStatus.CREATED.value());
			return ResponseEntity.ok().body(respostaDto);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException("Erro ao tentar cadastrar pessoa.", HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	@PostMapping(value = "/processar-doadores/{gravarDoadores}")
	public ResponseEntity<RespostaProcessamentoDoadoresDto> processarDoadores(@PathVariable boolean gravarDoadores,
			@RequestBody List<PessoaDto> listaPessoasDto) throws ApiException {

		try {
			return ResponseEntity.ok().body(service.processarDoadores(listaPessoasDto, gravarDoadores));

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException("Erro ao processar lista de doadores.", HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	@PutMapping
	public ResponseEntity<RespostaApiDto> atualizarPessoa(@RequestBody PessoaDto pessoaDto) throws ApiException {

		try {

			RespostaApiDto respostaDto = new RespostaApiDto();
			respostaDto.adicionarPessoa(service.atualizarPessoa(pessoaDto));
			respostaDto.setMsg("Pessoa atualizada com sucesso!!!");
			respostaDto.setStatus(HttpStatus.ACCEPTED.value());
			return ResponseEntity.ok().body(respostaDto);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException("Erro ao tentar atualizar pessoa.", HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<RespostaApiDto> deletePessoa(@PathVariable Long id) throws ApiException {

		try {
			return ResponseEntity.ok().body(service.deleteById(id));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException("Erro ao tentar deletar pessoa.", HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
}

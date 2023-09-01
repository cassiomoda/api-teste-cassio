package com.moda.apitestecassio.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.moda.apitestecassio.dto.PessoaDto;
import com.moda.apitestecassio.dto.RespostaApiDto;
import com.moda.apitestecassio.dto.RespostaProcessamentoDoadoresDto;
import com.moda.apitestecassio.entities.Pessoa;
import com.moda.apitestecassio.exceptions.ApiException;
import com.moda.apitestecassio.repositories.PessoaRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class PessoaService {

	public static final Integer IDADE_MINIMA = 16;
	public static final Integer IDADE_MAXIMA = 69;
	public static final Double PESO_MINIMO = 50.0;

	@Autowired
	private PessoaRepository repository;

	public List<Pessoa> findAll() {

		return repository.findAll();
	}

	public Pessoa findById(Long id) {

		Optional<Pessoa> obj = repository.findById(id);
		return obj.get();
	}

	private boolean validarPessoa(PessoaDto pessoaDto) throws ApiException {

		if (StringUtils.isBlank(pessoaDto.getNome())) {
			throw new ApiException("O campo Nome é obrigatorio.", HttpStatus.BAD_REQUEST.value());
		}

		if (StringUtils.isBlank(pessoaDto.getCpf())) {
			throw new ApiException("O campo CPF é obrigatorio.", HttpStatus.BAD_REQUEST.value());
		}

		return true;
	}

	private void copyPessoaDtoParaPessoa(PessoaDto pessoaDto, Pessoa pessoa) throws ApiException {

		pessoa.setAltura(pessoaDto.getAltura());
		pessoa.setBairro(pessoaDto.getBairro());
		pessoa.setCelular(pessoaDto.getCelular());
		pessoa.setCep(pessoaDto.getCep());
		pessoa.setCidade(pessoaDto.getCidade());
		pessoa.setCpf(pessoaDto.getCpf());
		pessoa.setEmail(pessoaDto.getEmail());
		pessoa.setEndereco(pessoaDto.getEndereco());
		pessoa.setEstado(pessoaDto.getEstado());
		pessoa.setMae(pessoaDto.getMae());
		pessoa.setNome(pessoaDto.getNome());
		pessoa.setNumero(pessoaDto.getNumero());
		pessoa.setPai(pessoaDto.getPai());
		pessoa.setPeso(pessoaDto.getPeso());
		pessoa.setRg(pessoaDto.getRg());
		pessoa.setSexo(pessoaDto.getSexo());
		pessoa.setTelefone_fixo(pessoaDto.getTelefone_fixo());
		pessoa.setTipo_sanguineo(pessoaDto.getTipo_sanguineo());

		if (StringUtils.isNotBlank(pessoaDto.getData_nasc())) {

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			try {
				pessoa.setData_nasc(format.parse(pessoaDto.getData_nasc()));

			} catch (Exception e) {
				e.printStackTrace();
				throw new ApiException("Data de nascimento inválida.", HttpStatus.BAD_REQUEST.value());
			}
		}
	}

	private void copyPessoaParaPessoaDto(Pessoa pessoa, PessoaDto pessoaDto) throws ApiException {

		pessoaDto.setAltura(pessoa.getAltura());
		pessoaDto.setBairro(pessoa.getBairro());
		pessoaDto.setCelular(pessoa.getCelular());
		pessoaDto.setCep(pessoa.getCep());
		pessoaDto.setCidade(pessoa.getCidade());
		pessoaDto.setCpf(pessoa.getCpf());
		pessoaDto.setEmail(pessoa.getEmail());
		pessoaDto.setEndereco(pessoa.getEndereco());
		pessoaDto.setEstado(pessoa.getEstado());
		pessoaDto.setMae(pessoa.getMae());
		pessoaDto.setNome(pessoa.getNome());
		pessoaDto.setNumero(pessoa.getNumero());
		pessoaDto.setPai(pessoa.getPai());
		pessoaDto.setPeso(pessoa.getPeso());
		pessoaDto.setRg(pessoa.getRg());
		pessoaDto.setSexo(pessoa.getSexo());
		pessoaDto.setTelefone_fixo(pessoa.getTelefone_fixo());
		pessoaDto.setTipo_sanguineo(pessoa.getTipo_sanguineo());

		if (pessoa.getData_nasc() != null) {

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			try {
				pessoaDto.setData_nasc(format.format(pessoa.getData_nasc()));

			} catch (Exception e) {
				e.printStackTrace();
				throw new ApiException("Data de nascimento inválida.", HttpStatus.BAD_REQUEST.value());
			}
		}
	}

	public Pessoa cadastrarPessoa(PessoaDto pessoaDto) throws ApiException {

		Pessoa pessoa = null;

		if (this.validarPessoa(pessoaDto)) {

			Optional<Pessoa> obj = repository.findByCpf(pessoaDto.getCpf());

			if (!obj.isEmpty()) {
				pessoa = obj.get();
			}

			if (pessoa == null) {
				pessoa = new Pessoa();
				copyPessoaDtoParaPessoa(pessoaDto, pessoa);
				pessoa = repository.save(pessoa);
			} else {
				pessoaDto.setId(pessoa.getId());
				this.atualizarPessoa(pessoaDto);
			}
		}

		return pessoa;
	}

	private boolean isDoador(PessoaDto pessoaDto) {

		boolean resultado = true;

		if (StringUtils.isBlank(pessoaDto.getCpf())) {
			resultado = false;
		}

		if (StringUtils.isBlank(pessoaDto.getTipo_sanguineo())) {
			resultado = false;
		}

		if (StringUtils.isBlank(pessoaDto.getData_nasc())) {
			resultado = false;
		}

		if (pessoaDto.getIdade() < IDADE_MINIMA || pessoaDto.getIdade() > IDADE_MAXIMA) {
			resultado = false;
		}

		if (pessoaDto.getPeso().compareTo(PESO_MINIMO) < 0) {
			resultado = false;
		}

		return resultado;
	}

	private String gerarFaixaDeIdade(Integer idade) {

		String faixa = "";

		if (idade.toString().length() == 1) {
			faixa = "0-10";

		} else if (idade.toString().length() == 2) {

			String digitoInicialStr = idade.toString().substring(0, 1);
			Integer digitoInicial = Integer.parseInt(digitoInicialStr);

			switch (digitoInicial) {
			case 1:
				faixa = "11-20";
				break;
			case 2:
				faixa = "21-30";
				break;
			case 3:
				faixa = "31-40";
				break;
			case 4:
				faixa = "41-50";
				break;
			case 5:
				faixa = "51-60";
				break;
			case 6:
				faixa = "61-70";
				break;
			default:
				faixa = "";
			}
		}

		return faixa;
	}

	private List<String> listarTiposSaguineosCompativeis(String tipoSanguineo) {

		List<String> tiposCompativeis = new ArrayList<>();

		switch (tipoSanguineo.toUpperCase()) {
		case "A+":
			tiposCompativeis.add("A+");
			tiposCompativeis.add("A-");
			tiposCompativeis.add("O+");
			tiposCompativeis.add("O-");
			break;
		case "A-":
			tiposCompativeis.add("A-");
			tiposCompativeis.add("O-");
			break;
		case "B+":
			tiposCompativeis.add("B+");
			tiposCompativeis.add("B-");
			tiposCompativeis.add("O+");
			tiposCompativeis.add("O-");
			break;
		case "B-":
			tiposCompativeis.add("B-");
			tiposCompativeis.add("O-");
			break;
		case "AB+":
			tiposCompativeis.add("A+");
			tiposCompativeis.add("B+");
			tiposCompativeis.add("O+");
			tiposCompativeis.add("AB+");
			tiposCompativeis.add("A-");
			tiposCompativeis.add("B-");
			tiposCompativeis.add("O-");
			tiposCompativeis.add("AB-");
			break;
		case "AB-":
			tiposCompativeis.add("A-");
			tiposCompativeis.add("B-");
			tiposCompativeis.add("O-");
			tiposCompativeis.add("AB-");
			break;
		case "O+":
			tiposCompativeis.add("O+");
			tiposCompativeis.add("O-");
			break;
		case "O-":
			tiposCompativeis.add("O-");
			break;
		}

		return tiposCompativeis;
	}

	public RespostaProcessamentoDoadoresDto processarDoadores(List<PessoaDto> listaPessoasDto, boolean gravarPessoas)
			throws ApiException {

		RespostaProcessamentoDoadoresDto respostaDto = new RespostaProcessamentoDoadoresDto();

		List<String> listCpfs = new ArrayList<>();
		Map<String, Integer> qtdDoadoresPorEstado = new HashMap<>();

		Map<String, Double> imcMedioPorFaixaIdade = new HashMap<>();
		Map<String, Double> somaImcPorFaixaIdade = new HashMap<>();
		Map<String, Integer> qtdDoadoPorFaixaIdade = new HashMap<>();

		Map<String, Double> percentualObesosPorSexo = new HashMap<>();
		Map<String, Integer> qtdDoadoresPorSexo = new HashMap<>();
		Map<String, Integer> qtdObesosPorSexo = new HashMap<>();

		Map<String, Double> mediaIdadePorTipoSanguineo = new HashMap<>();
		Map<String, Integer> qtdDoadoresPorTipoSanguineo = new HashMap<>();
		Map<String, Integer> somaIdadePorTipoSanguineo = new HashMap<>();

		Map<String, Integer> qtdPossiveisDoadoresPorTipoSanguineo = new HashMap<>();

		for (PessoaDto pessoaDto : listaPessoasDto) {

			if (!isDoador(pessoaDto) || listCpfs.contains(pessoaDto.getCpf())) {
				continue;
			}

			if (gravarPessoas) {
				this.cadastrarPessoa(pessoaDto);
			}

			listCpfs.add(pessoaDto.getCpf());
			Integer qtdDoadores = 0;

			if (StringUtils.isNotBlank(pessoaDto.getEstado())) {

				qtdDoadores = qtdDoadoresPorEstado.get(pessoaDto.getEstado());

				if (qtdDoadores == null) {
					qtdDoadores = 0;
				}

				qtdDoadores++;
				qtdDoadoresPorEstado.put(pessoaDto.getEstado(), qtdDoadores);
			}

			String faixaIdade = this.gerarFaixaDeIdade(pessoaDto.getIdade());
			qtdDoadores = 0;
			qtdDoadores = qtdDoadoPorFaixaIdade.get(this.gerarFaixaDeIdade(pessoaDto.getIdade()));

			if (qtdDoadores == null) {
				qtdDoadores = 0;
			}

			qtdDoadores++;
			qtdDoadoPorFaixaIdade.put(faixaIdade, qtdDoadores);

			Double somaImc = somaImcPorFaixaIdade.get(faixaIdade);

			if (somaImc == null) {
				somaImc = 0.0;
			}

			Double imcDoador = 0.0;

			if (pessoaDto.getPeso().compareTo(0.0) > 0 && pessoaDto.getAltura().compareTo(0.0) > 0) {
				imcDoador = pessoaDto.getPeso() / Math.pow(pessoaDto.getAltura(), 2);
				somaImc += imcDoador;
			}

			somaImcPorFaixaIdade.put(faixaIdade, somaImc);

			Double mediaImc = somaImc / Double.valueOf(qtdDoadores);
			imcMedioPorFaixaIdade.put(faixaIdade, mediaImc);

			if (StringUtils.isNotBlank(pessoaDto.getSexo())) {
				qtdDoadores = 0;
				qtdDoadores = qtdDoadoresPorSexo.get(pessoaDto.getSexo());

				if (qtdDoadores == null) {
					qtdDoadores = 0;
				}

				qtdDoadores++;
				qtdDoadoresPorSexo.put(pessoaDto.getSexo(), qtdDoadores);

				if (imcDoador.compareTo(30.0) > 0) {
					qtdDoadores = 0;
					qtdDoadores = qtdObesosPorSexo.get(pessoaDto.getSexo());

					if (qtdDoadores == null) {
						qtdDoadores = 0;
					}

					qtdDoadores++;
					qtdObesosPorSexo.put(pessoaDto.getSexo(), qtdDoadores);
				}

				Integer qtdTotDoadoresPorSexo = qtdDoadoresPorSexo.get(pessoaDto.getSexo());
				if (qtdTotDoadoresPorSexo == null) {
					qtdTotDoadoresPorSexo = 0;
				}

				Integer qtdTotObesosPorSexo = qtdObesosPorSexo.get(pessoaDto.getSexo());
				if (qtdTotObesosPorSexo == null) {
					qtdTotObesosPorSexo = 0;
				}

				Double percentual = (Double.valueOf(qtdTotObesosPorSexo) / Double.valueOf(qtdTotDoadoresPorSexo))
						* 100.0;
				percentualObesosPorSexo.put(pessoaDto.getSexo(), percentual);
			}

			qtdDoadores = 0;
			qtdDoadores = qtdDoadoresPorTipoSanguineo.get(pessoaDto.getTipo_sanguineo());

			if (qtdDoadores == null) {
				qtdDoadores = 0;
			}

			qtdDoadores++;
			qtdDoadoresPorTipoSanguineo.put(pessoaDto.getTipo_sanguineo(), qtdDoadores);

			Integer somaIdade = somaIdadePorTipoSanguineo.get(pessoaDto.getTipo_sanguineo());
			if (somaIdade == null) {
				somaIdade = 0;
			}

			somaIdade += pessoaDto.getIdade();
			somaIdadePorTipoSanguineo.put(pessoaDto.getTipo_sanguineo(), somaIdade);

			Double mediaIdade = (double) (somaIdade / qtdDoadores);
			mediaIdadePorTipoSanguineo.put(pessoaDto.getTipo_sanguineo(), mediaIdade);

			Integer qtdPossiveisDoadores = 0;
			for (String tipoCompativel : this.listarTiposSaguineosCompativeis(pessoaDto.getTipo_sanguineo())) {

				qtdDoadores = qtdDoadoresPorTipoSanguineo.get(tipoCompativel);

				if (qtdDoadores == null) {
					qtdDoadores = 0;
				}

				qtdPossiveisDoadores += qtdDoadores;
			}

			qtdPossiveisDoadoresPorTipoSanguineo.put(pessoaDto.getTipo_sanguineo(), qtdPossiveisDoadores);
		}

		respostaDto.setQtdDoadoresPorEstado(new TreeMap<String, Integer>(qtdDoadoresPorEstado));
		respostaDto.setImcMedioPorFaixaIdade(new TreeMap<String, Double>(imcMedioPorFaixaIdade));
		respostaDto.setPercentualObesosPorSexo(new TreeMap<String, Double>(percentualObesosPorSexo));
		respostaDto.setMediaIdadePorTipoSanguineo(new TreeMap<String, Double>(mediaIdadePorTipoSanguineo));
		respostaDto.setQtdPossiveisDoadoresPorTipoSanguineo(new TreeMap<String, Integer>(qtdPossiveisDoadoresPorTipoSanguineo));
		return respostaDto;
	}

	private List<PessoaDto> converterListaPessoaParaPessoaDto(List<Pessoa> listaPessoas) throws ApiException {

		List<PessoaDto> listaPessoasDto = new ArrayList<>();

		for (Pessoa pessoa : listaPessoas) {

			PessoaDto pessoaDto = new PessoaDto();
			this.copyPessoaParaPessoaDto(pessoa, pessoaDto);
			listaPessoasDto.add(pessoaDto);
		}

		return listaPessoasDto;
	}

	public RespostaProcessamentoDoadoresDto processarDoadoresGravados() throws ApiException {

		List<Pessoa> listPessoas = repository.findAll();
		return this.processarDoadores(this.converterListaPessoaParaPessoaDto(listPessoas), false);
	}

	public Pessoa atualizarPessoa(PessoaDto pessoaDto) throws ApiException {

		Pessoa pessoa = null;

		if (this.validarPessoa(pessoaDto)) {

			if (pessoaDto.getId() == null || pessoaDto.getId().compareTo(0L) < 0) {
				throw new ApiException("Registro não atualizado. O Identificador da pessoa não foi enviado.",
						HttpStatus.BAD_REQUEST.value());
			}

			Optional<Pessoa> obj = repository.findById(pessoaDto.getId());

			if (obj.isEmpty()) {
				throw new ApiException("A pessoa informada não foi localizada. Verifique.",
						HttpStatus.BAD_REQUEST.value());
			}

			pessoa = obj.get();
			copyPessoaDtoParaPessoa(pessoaDto, pessoa);
			pessoa = repository.save(pessoa);
		}

		return pessoa;
	}

	public RespostaApiDto deleteById(Long id) throws ApiException {

		try {
			Optional<Pessoa> obj = repository.findById(id);

			if (obj.isEmpty()) {
				throw new ApiException("O Id informado não corresponde a uma pessoa cadastrada.",
						HttpStatus.BAD_REQUEST.value());
			}

			repository.deleteById(id);

			RespostaApiDto respostaDto = new RespostaApiDto();
			respostaDto.setStatus(HttpStatus.OK.value());
			respostaDto.setMsg("Pessoa deletada com sucesso!");
			respostaDto.adicionarPessoa(obj.get());
			return respostaDto;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException("Erro ao tentar deletar Pessoa.", HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
}

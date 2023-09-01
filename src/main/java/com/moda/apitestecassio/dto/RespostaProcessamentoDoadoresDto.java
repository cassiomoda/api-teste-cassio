package com.moda.apitestecassio.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class RespostaProcessamentoDoadoresDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private TreeMap<String, Integer> qtdDoadoresPorEstado;
	private TreeMap<String, Double> imcMedioPorFaixaIdade;
	private TreeMap<String, Double> percentualObesosPorSexo;
	private TreeMap<String, Double> mediaIdadePorTipoSanguineo;
	private TreeMap<String, Integer> qtdPossiveisDoadoresPorTipoSanguineo;

	public RespostaProcessamentoDoadoresDto() {
	}

	public Map<String, Integer> getQtdDoadoresPorEstado() {
		return qtdDoadoresPorEstado;
	}

	public void setQtdDoadoresPorEstado(TreeMap<String, Integer> qtdDoadoresPorEstado) {
		this.qtdDoadoresPorEstado = qtdDoadoresPorEstado;
	}

	public Map<String, Double> getImcMedioPorFaixaIdade() {
		return imcMedioPorFaixaIdade;
	}

	public void setImcMedioPorFaixaIdade(TreeMap<String, Double> imcMedioPorFaixaIdade) {
		this.imcMedioPorFaixaIdade = imcMedioPorFaixaIdade;
	}

	public Map<String, Double> getPercentualObesosPorSexo() {
		return percentualObesosPorSexo;
	}

	public void setPercentualObesosPorSexo(TreeMap<String, Double> percentualObesosPorSexo) {
		this.percentualObesosPorSexo = percentualObesosPorSexo;
	}

	public Map<String, Double> getMediaIdadePorTipoSanguineo() {
		return mediaIdadePorTipoSanguineo;
	}

	public void setMediaIdadePorTipoSanguineo(TreeMap<String, Double> mediaIdadePorTipoSanguineo) {
		this.mediaIdadePorTipoSanguineo = mediaIdadePorTipoSanguineo;
	}

	public Map<String, Integer> getQtdPossiveisDoadoresPorTipoSanguineo() {
		return qtdPossiveisDoadoresPorTipoSanguineo;
	}

	public void setQtdPossiveisDoadoresPorTipoSanguineo(TreeMap<String, Integer> qtdPossiveisDoadoresPorTipoSanguineo) {
		this.qtdPossiveisDoadoresPorTipoSanguineo = qtdPossiveisDoadoresPorTipoSanguineo;
	}
}

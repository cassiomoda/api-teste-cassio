package com.moda.apitestecassio.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.moda.apitestecassio.entities.Pessoa;

public class RespostaApiDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;
	private String msg;
	private List<Pessoa> listaPessoas = new ArrayList<>();

	public RespostaApiDto() {
	}

	public RespostaApiDto(Integer status, String msg) {

		this.status = status;
		this.msg = msg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void adicionarPessoa(Pessoa pessoa) {
		this.listaPessoas.add(pessoa);
	}

	public void removerPessoa(Pessoa pessoa) {
		this.listaPessoas.remove(pessoa);
	}

}

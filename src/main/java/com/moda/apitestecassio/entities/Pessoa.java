package com.moda.apitestecassio.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 255, nullable = false)
	private String nome;

	@Column(length = 15, nullable = false)
	private String cpf;

	@Column(length = 12)
	private String rg;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data_nasc;

	@Column(length = 10)
	private String sexo;

	@Column(length = 255)
	private String mae;

	@Column(length = 255)
	private String pai;

	@Column(length = 255)
	private String email;

	@Column(length = 10)
	private String cep;

	@Column(length = 255)
	private String endereco;

	@Column(length = 5)
	private String numero;

	@Column(length = 255)
	private String bairro;

	@Column(length = 255)
	private String cidade;

	@Column(length = 2)
	private String estado;

	@Column(length = 20)
	private String telefone_fixo;

	@Column(length = 20)
	private String celular;

	@Column
	private Double altura;

	@Column
	private Double peso;

	@Column(length = 5)
	private String tipo_sanguineo;

	public Pessoa() {
	}

	public Pessoa(Long id, String nome, String cpf, String rg, Date data_nasc, String sexo, String mae, String pai,
			String email, String cep, String endereco, String numero, String bairro, String cidade, String estado,
			String telefone_fixo, String celular, Double altura, Double peso, String tipo_sanguineo) {

		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.data_nasc = data_nasc;
		this.sexo = sexo;
		this.mae = mae;
		this.pai = pai;
		this.email = email;
		this.cep = cep;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.telefone_fixo = telefone_fixo;
		this.celular = celular;
		this.altura = altura;
		this.peso = peso;
		this.tipo_sanguineo = tipo_sanguineo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefone_fixo() {
		return telefone_fixo;
	}

	public void setTelefone_fixo(String telefone_fixo) {
		this.telefone_fixo = telefone_fixo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getTipo_sanguineo() {
		return tipo_sanguineo;
	}

	public void setTipo_sanguineo(String tipo_sanguineo) {
		this.tipo_sanguineo = tipo_sanguineo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}
}

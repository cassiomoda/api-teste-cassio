package com.moda.apitestecassio.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import org.hibernate.validator.constraints.Length;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PessoaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank
	@NotNull
	@Length(min = 3, max = 255)
	private String nome;

	@NotBlank
	@NotNull
	@Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")
	private String cpf;

	@Length(max = 12)
	private String rg;

	@Pattern(regexp = "(\\d{2}/\\d{2}/\\d{4})?")
	private String data_nasc;

	@Pattern(regexp = "(Feminino|Masculino)?")
	private String sexo;

	@Length(max = 255)
	private String mae;

	@Length(max = 255)
	private String pai;

	@Length(max = 255)
	@Email
	private String email;

	@Pattern(regexp = "(\\d{5}-\\d{3})?")
	private String cep;

	@Length(max = 255)
	private String endereco;

	@Length(max = 5)
	private String numero;

	@Length(max = 255)
	private String bairro;

	@Length(max = 255)
	private String cidade;

	@Length(max = 2)
	private String estado;

	@Length(max = 20)
	private String telefone_fixo;

	@Length(max = 20)
	private String celular;

	private Double altura;

	private Double peso;

	@Length(max = 5)
	private String tipo_sanguineo;

	@NotNull
	@Length(max = 10)
	@Pattern(regexp = "Ativo|Inativo")
	private String status = "Ativo";

	public PessoaDto() {
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

	public String getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(String data_nasc) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		PessoaDto other = (PessoaDto) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}

	public Date getDataNascimento() {
		if (StringUtils.isBlank(data_nasc)) {
			return null;
		} else {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			try {
				return format.parse(data_nasc);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public Integer getIdade() {

		if (data_nasc == null) {
			return 0;
		} else {
			Calendar calendar = Calendar.getInstance();

			calendar.setTime(this.getDataNascimento());
			Integer anoNascimento = calendar.get(Calendar.YEAR);

			calendar.setTime(new Date());
			Integer anoAtual = calendar.get(Calendar.YEAR);

			return anoAtual - anoNascimento;
		}
	}
}

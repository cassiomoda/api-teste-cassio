package com.moda.apitestecassio.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moda.apitestecassio.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	public Optional<Pessoa> findByCpf(String cpf); 
}

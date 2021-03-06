package com.example.pessoa.apirest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pessoa.apirest.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	Optional<Pessoa> findByCPF(String cpf);
	Optional<Pessoa> findByemail(String email);
}

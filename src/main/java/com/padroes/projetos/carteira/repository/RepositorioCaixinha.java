package com.padroes.projetos.carteira.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;

public interface RepositorioCaixinha extends JpaRepository<Caixinha, Long> {

}

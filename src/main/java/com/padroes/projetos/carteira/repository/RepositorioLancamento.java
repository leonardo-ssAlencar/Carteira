package com.padroes.projetos.carteira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;

@Repository
public interface RepositorioLancamento extends JpaRepository<Lancamento, Long> {

}
